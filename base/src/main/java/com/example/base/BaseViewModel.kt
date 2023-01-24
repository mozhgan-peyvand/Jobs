package com.example.base

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.selects.select
import java.util.concurrent.Executors
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.reflect.KProperty1

abstract class BaseViewModel<S, A> constructor(initialState: S) : ViewModel() {

    private val setStateChannel = Channel<S.() -> S>(capacity = Channel.UNLIMITED)
    private val withStateChannel = Channel<(S) -> Unit>(capacity = Channel.UNLIMITED)

    private val actionSharedFlow = MutableSharedFlow<A>()
    private val stateSharedFlow = MutableSharedFlow<S>(
        replay = 1,
        extraBufferCapacity = SubscriberBufferSize,
        onBufferOverflow = BufferOverflow.SUSPEND,
    ).apply { tryEmit(initialState) }

    /**
     * Synchronous access to state is not exposed externally because there is no guarantee that
     * all setState reducers have run yet.
     */
    internal var state: S = initialState

    /**
     * Return the current state as a Flow. For certain situations, this may be more convenient
     * than subscribe and selectSubscribe because it can easily be composed with other
     * coroutines operations and chained with operators.
     *
     * This WILL emit the current state followed by all subsequent state updates.
     *
     * This is not a StateFlow to prevent the ViewModel from having synchronous access to state. withState { state -> } should
     * be used as it is guaranteed to be run after all pending setState reducers have run.
     */
    val stateFlow: Flow<S> = stateSharedFlow.asSharedFlow()

    private val _effectSharedFlow = MutableSharedFlow<BaseEffect>()
    val effectSharedFlow = _effectSharedFlow.asSharedFlow()

    protected fun setEffect(effect: BaseEffect) = viewModelScope.launch {
        _effectSharedFlow.emit(effect)
    }

    init {
        setupTriggerFlushQueues()
    }

    fun submitAction(action: A) {
        viewModelScope.launch {
            actionSharedFlow.emit(action)
        }
    }

    /**
     * Call this to mutate the current state.
     * It will not be called synchronously or on the same thread. This is for performance and accuracy reasons.
     */
    protected fun setState(reducer: S.() -> S) {
        setStateChannel.trySend(reducer)
        if (FORCE_SYNCHRONOUS_STATE_STORES) {
            flushQueuesOnceBlocking()
        }
    }

    /**
     * Calling this function suspends until all pending setState reducers are run and then returns the latest state.
     * As a result, it is safe to call setState { } and assume that the result from a subsequent awaitState() call will have that state.
     */
    suspend fun awaitState(): S {
        val deferredState = CompletableDeferred<S>()
        withState(deferredState::complete)
        return deferredState.await()
    }

    /**
     * Access the current ViewModel state. Takes a block of code that will be run after all current pending state
     * updates are processed.
     */
    fun withState(action: (state: S) -> Unit) {
        withStateChannel.trySend(action)
        if (FORCE_SYNCHRONOUS_STATE_STORES) {
            flushQueuesOnceBlocking()
        }
    }

    /**
     * Run a coroutine and wrap its progression with [AsyncResult] property reduced to the global state.
     *
     * @param dispatcher A custom coroutine dispatcher that the coroutine will run on. If null, uses the dispatcher in [viewModelScope],
     *                  which defaults to [Dispatchers.Main.immediate].
     * @param retainValue A state property that, when set, will be called to retrieve an optional existing data value that will be retained across
     *                    subsequent Loading and Fail states. This is useful if you want to display the previously successful data when
     *                    refreshing.
     * @param reducer A reducer that is applied to the current state and should return the new state. Because the state is the receiver
     *                and is likely a data class, an implementation may look like: `{ copy(response = it) }`.
     */
    protected open fun <T : Any?> Deferred<T>.execute(
        dispatcher: CoroutineDispatcher? = null,
        retainValue: KProperty1<S, AsyncResult<T>>? = null,
        reducer: S.(AsyncResult<T>) -> S,
    ) = suspend { await() }.execute(dispatcher, retainValue, reducer)

    /**
     * Run a coroutine and wrap its progression with [AsyncResult] property reduced to the global state.
     *
     * @param dispatcher A custom coroutine dispatcher that the coroutine will run on. If null, uses the dispatcher in [viewModelScope],
     *                  which defaults to [Dispatchers.Main.immediate].
     * @param retainValue A state property that, when set, will be called to retrieve an optional existing data value that will be retained across
     *                    subsequent Loading and Fail states. This is useful if you want to display the previously successful data when
     *                    refreshing.
     * @param reducer A reducer that is applied to the current state and should return the new state. Because the state is the receiver
     *                and is likely a data class, an implementation may look like: `{ copy(response = it) }`.
     */
    protected open fun <T : Any?> (suspend () -> T).execute(
        dispatcher: CoroutineDispatcher? = null,
        retainValue: KProperty1<S, AsyncResult<T>>? = null,
        reducer: S.(AsyncResult<T>) -> S
    ): Job {
        setState { reducer(Loading(value = retainValue?.get(this)?.invoke())) }

        return viewModelScope.launch(dispatcher ?: EmptyCoroutineContext) {
            try {
                val result = invoke()
                setState { reducer(Success(result)) }
            } catch (error: Throwable) {
                setState { reducer(Fail(error, value = retainValue?.get(this)?.invoke())) }
            }
        }
    }

    /**
     * Run a coroutine and wrap its progression with [AsyncResult] property reduced to the global state.
     *
     * @param dispatcher A custom coroutine dispatcher that the coroutine will run on. If null, uses the dispatcher in [viewModelScope],
     *                  which defaults to [Dispatchers.Main.immediate].
     * @param retainValue A state property that, when set, will be called to retrieve an optional existing data value that will be retained across
     *                    subsequent Loading and Fail states. This is useful if you want to display the previously successful data when
     *                    refreshing.
     * @param transform Transform result on success condition
     * @param reducer A reducer that is applied to the current state and should return the new state. Because the state is the receiver
     *                and is likely a data class, an implementation may look like: `{ copy(response = it) }`.
     */
    protected open fun <T : Any?, R : Any?> (suspend () -> T).execute(
        dispatcher: CoroutineDispatcher? = null,
        retainValue: KProperty1<S, AsyncResult<R>>? = null,
        transform: T.() -> R,
        reducer: S.(AsyncResult<R>) -> S
    ): Job {
        setState { reducer(Loading(value = retainValue?.get(this)?.invoke())) }

        return viewModelScope.launch(dispatcher ?: EmptyCoroutineContext) {
            try {
                val result = invoke()
                setState { reducer(Success(transform(result))) }
            } catch (error: Throwable) {
                setState { reducer(Fail(error, value = retainValue?.get(this)?.invoke())) }
            }
        }
    }

    /**
     * Run a coroutine and return value or caught error through callbacks.
     *
     * @param dispatcher A custom coroutine dispatcher that the coroutine will run on. If null, uses the dispatcher in [viewModelScope],
     *                  which defaults to [Dispatchers.Main.immediate].
     * @param onSuccess Trigger once operation is complete and return result value.
     * @param onFailure Trigger when exception happens during operation.
     */
    protected open fun <T : Any?> (suspend () -> T).execute(
        onSuccess: (suspend (T) -> Unit)? = null,
        onFailure: (suspend (Throwable) -> Unit)? = null,
        dispatcher: CoroutineDispatcher? = null
    ): Job {
        assert(onSuccess != null || onFailure != null) { "onSuccess and onFail both cannot be null" }
        return viewModelScope.launch(dispatcher ?: EmptyCoroutineContext) {
            try {
                val result = invoke()
                onSuccess?.invoke(result)
            } catch (error: Throwable) {
                onFailure?.invoke(error)
            }
        }
    }

    /**
     * Collect a Flow and wrap its progression with [AsyncResult] property reduced to the global state.
     *
     * @param dispatcher A custom coroutine dispatcher that the coroutine will run on. If null, uses the dispatcher in [viewModelScope],
     *                  which defaults to [Dispatchers.Main.immediate].
     * @param retainValue A state property that, when set, will be called to retrieve an optional existing data value that will be retained across
     *                    subsequent Loading and Fail states. This is useful if you want to display the previously successful data when
     *                    refreshing.
     * @param reducer A reducer that is applied to the current state and should return the new state. Because the state is the receiver
     *                and is likely a data class, an implementation may look like: `{ copy(response = it) }`.
     */
    protected open fun <T> Flow<T>.execute(
        dispatcher: CoroutineDispatcher? = null,
        retainValue: KProperty1<S, AsyncResult<T>>? = null,
        reducer: S.(AsyncResult<T>) -> S,
    ): Job {
        setState { reducer(Loading(value = retainValue?.get(this)?.invoke())) }

        return catch { error ->
            setState { reducer(Fail(error, value = retainValue?.get(this)?.invoke())) }
        }
            .onEach { value -> setState { reducer(Success(value)) } }
            .launchIn(viewModelScope + (dispatcher ?: EmptyCoroutineContext))
    }

    /**
     * Collect a Flow and update state each time it emits a value. This is functionally the same as wrapping onEach with a setState call.
     *
     * @param dispatcher A custom coroutine dispatcher that the coroutine will run on. If null, uses the dispatcher in [viewModelScope],
     *                  which defaults to [Dispatchers.Main.immediate].
     * @param reducer A reducer that is applied to the current state and should return the new state. Because the state is the receiver
     *                and is likely a data class, an implementation may look like: `{ copy(response = it) }`.
     */
    protected open fun <T> Flow<T>.setOnEach(
        dispatcher: CoroutineDispatcher? = null,
        reducer: S.(T) -> S,
    ): Job {
        return onEach {
            setState { reducer(it) }
        }.launchIn(viewModelScope + (dispatcher ?: EmptyCoroutineContext))
    }

    /**
     * Subscribe to all action changes.
     *
     * @param action supports cooperative cancellation. The previous action will be cancelled if it as not completed before
     * the next one is emitted.
     */
    protected fun onEachAction(
        action: suspend (A) -> Unit,
    ): Job {
        return viewModelScope.launch {
            actionSharedFlow.collectLatest(action)
        }
    }

    /**
     * Subscribe to all state changes.
     *
     * @param action supports cooperative cancellation. The previous action will be cancelled if it as not completed before
     * the next one is emitted.
     */
    protected fun onEachState(
        action: suspend (S) -> Unit,
    ) = stateFlow.resolveSubscription(action)

    /**
     * Subscribe to state changes for a single property.
     *
     * @param action supports cooperative cancellation. The previous action will be cancelled if it as not completed before
     * the next one is emitted.
     */
    protected fun <A> onEachState(
        prop1: KProperty1<S, A>,
        action: suspend (A) -> Unit,
    ) = stateFlow
        .map { prop1.get(it) }
        .distinctUntilChanged()
        .resolveSubscription(action)

    /**
     * Subscribe to state changes for two properties.
     *
     * @param action supports cooperative cancellation. The previous action will be cancelled if it as not completed before
     * the next one is emitted.
     */
    protected fun <A, B> onEachState(
        prop1: KProperty1<S, A>,
        prop2: KProperty1<S, B>,
        action: suspend (A, B) -> Unit,
    ) = stateFlow
        .map { Pair(prop1.get(it), prop2.get(it)) }
        .distinctUntilChanged()
        .resolveSubscription { (a, b) ->
            action(a, b)
        }

    /**
     * Subscribe to state changes for three properties.
     *
     * @param action supports cooperative cancellation. The previous action will be cancelled if it as not completed before
     * the next one is emitted.
     */
    protected fun <A, B, C> onEachState(
        prop1: KProperty1<S, A>,
        prop2: KProperty1<S, B>,
        prop3: KProperty1<S, C>,
        action: suspend (A, B, C) -> Unit,
    ) = stateFlow
        .map { Triple(prop1.get(it), prop2.get(it), prop3.get(it)) }
        .distinctUntilChanged()
        .resolveSubscription { (a, b, c) ->
            action(a, b, c)
        }

    /**
     * Subscribe to changes in an async property. There are optional parameters for onSuccess
     * and onFail which automatically unwrap the value or error.
     *
     * @param onFail supports cooperative cancellation. The previous action will be cancelled if it as not completed before
     * the next one is emitted.
     * @param onSuccess supports cooperative cancellation. The previous action will be cancelled if it as not completed before
     * the next one is emitted.
     */
    protected fun <T> onAsyncResult(
        asyncProp: KProperty1<S, AsyncResult<T>>,
        onFail: (suspend (Throwable) -> Unit)? = null,
        onSuccess: (suspend (T) -> Unit)? = null,
    ) = stateFlow
        .map { asyncProp.get(it) }
        .distinctUntilChanged()
        .resolveSubscription { asyncValue ->
            if (onSuccess != null && asyncValue is Success) {
                onSuccess(asyncValue())
            } else if (onFail != null && asyncValue is Fail) {
                onFail(asyncValue.error)
            }
        }

    private fun <T> Flow<T>.resolveSubscription(action: suspend (T) -> Unit): Job {
        return viewModelScope.launch(start = CoroutineStart.UNDISPATCHED) {
            // Use yield to ensure flow collect coroutine is dispatched rather than invoked immediately.
            // This is necessary when Dispatchers.Main.immediate is used in scope.
            // Coroutine is launched with start = CoroutineStart.UNDISPATCHED to perform dispatch only once.
            yield()
            this@resolveSubscription.collectLatest(action)
        }
    }

    /**
     * Poll [withStateChannel] and [setStateChannel] to respond to set/get state requests.
     */
    private fun setupTriggerFlushQueues() {
        if (FORCE_SYNCHRONOUS_STATE_STORES) return

        viewModelScope.launch(flushDispatcher) {
            while (isActive) {
                flushQueuesOnce()
            }
        }
    }

    /**
     * Flush the setState and withState queues.
     * All pending setState reducers will be run prior to every single withState lambda.
     * This ensures that situations such as the following will work correctly:
     *
     * Situation 1
     *
     * setState { ... }
     * withState { ... }
     *
     * Situation 2
     *
     * withState {
     *     setState { ... }
     *     withState { ... }
     * }
     */
    private suspend fun flushQueuesOnce() {
        select<Unit> {
            setStateChannel.onReceive { reducer ->
                val newState = state.reducer()
                if (newState != state) {
                    state = newState
                    stateSharedFlow.emit(newState)
                }
            }
            withStateChannel.onReceive { block ->
                block(state)
            }
        }
    }

    private fun flushQueuesOnceBlocking() {
        if (viewModelScope.isActive) {
            runBlocking { flushQueuesOnce() }
        }
    }

    @VisibleForTesting
    fun setStateForTesting(action: S.() -> S) {
        val result = action(state)
        setStateChannel.trySend { result }
    }

    companion object {
        private val flushDispatcher: CoroutineDispatcher =
            Executors.newCachedThreadPool().asCoroutineDispatcher()

        /**
         * The buffer size that will be allocated by [MutableSharedFlow].
         * If it falls behind by more than 64 state updates, it will start suspending.
         * Slow consumers should consider using `stateFlow.buffer(onBufferOverflow = BufferOverflow.DROP_OLDEST)`.
         *
         * The internally allocated buffer is replay + extraBufferCapacity but always allocates 2^n space.
         * We use replay=1 so buffer = 64-1.
         */
        internal const val SubscriberBufferSize = 63

        internal var FORCE_SYNCHRONOUS_STATE_STORES = false
    }
}
