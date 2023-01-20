package com.example.base.api



sealed class Resource<out R> {

    object None : Resource<Nothing>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val error: Exception) : Resource<Nothing>()
    data class Loading(val loading: Boolean) : Resource<Nothing>()
    object Canceled : Resource<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$error]"
            is Loading -> "Loading"
            Canceled -> "Canceled"
            None -> "None"
        }
    }

    fun isLoading() = this is Loading
    fun isSuccess() = this is Success
    fun isError() = this is Error
    fun isCanceled() = this is Canceled
}


inline fun <R> Resource<R>.onLoading(action: (Boolean) -> Unit): Resource<R> {
    if (this is Resource.Loading) {
        action(true)
    } else if (this !is Nothing) action(false)
    return this
}

inline fun <T> Resource<T>.onError(onFailure: (Exception) -> Unit) {
    if (this is Resource.Error) onFailure(error)
}

inline fun <R> Resource<R>.onSuccess(action: (R) -> Unit): Resource<R> {
    if (this is Resource.Success) {
        action(data)
    }
    return this
}

fun <R> Resource<R>.getDataOrException(): Resource.Success<R> {
    return (this as Resource.Success)
}

fun <T> Resource<T>.successOr(fallback: T): T {
    return (this as? Resource.Success<T>)?.data ?: fallback
}

suspend fun <T> Resource<T>.merge(action: suspend () -> Unit): Resource<T> {
    let {
        it.onSuccess {
            action.invoke()
        }
        it.onError { error ->
            throw error
        }
    }
    return this
}


