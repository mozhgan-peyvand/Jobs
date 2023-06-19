package com.example.ui.jobs.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavHostController
import com.example.base.models.JobDto
import com.example.base.util.*
import com.example.base.util.toolbar.CollapsingToolbarScaffold
import com.example.base.util.toolbar.ScrollStrategy
import com.example.base.util.toolbar.rememberCollapsingToolbarScaffoldState
import com.example.common.ui.view.dialog.AlertDialogSample
import com.example.ui.jobs.models.JobScreenState
import com.example.ui.jobs.models.JobScreenUiEvent
import com.example.ui.jobs.util.ui.OnBottomReached
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.example.base.R as BaseR


@Composable
fun JobScreen(
    viewModel: JobViewModel,
    navController: NavHostController
) {

    val viewState by viewModel.stateFlow.collectAsState(initial = JobScreenState())
    JobScreenList(
        actioner = { action ->
            viewModel.submitAction(action)
        },
        viewState = viewState,
        navController
    )
}

@Composable
fun JobScreenList(
    actioner: (JobScreenUiEvent) -> Unit,
    viewState: JobScreenState,
    navController: NavHostController,
) {

    val closeSearch = rememberSaveable {
        mutableStateOf(true)
    }

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState
    ) { paddingValues ->

        JobList(
            modifier = Modifier.padding(paddingValues),
            viewState = viewState, closeSearch = { closeSearch.value = it },
            closeSearchValue = closeSearch.value,
            actioner = actioner,
            navController = navController
        )
    }
}

@Composable
private fun JobList(
    modifier: Modifier,
    viewState: JobScreenState,
    closeSearch: (Boolean) -> Unit,
    closeSearchValue: Boolean,
    actioner: (JobScreenUiEvent) -> Unit,
    navController: NavHostController,
) {
    val state = rememberCollapsingToolbarScaffoldState()
    val lazyListState = rememberLazyListState()
    val swipeRefreshState =
        rememberSwipeRefreshState(viewState.JobList is Loading || viewState.insertJobList is Loading)

    CollapsingToolbarScaffold(
        modifier = Modifier
            .fillMaxSize(),
        state = state,
        scrollStrategy = ScrollStrategy.EnterAlways,
        toolbar = {
            JobTopBar(
                actioner = { searchText -> actioner(JobScreenUiEvent.SearchJobsList(searchText)) },
                closeSearch = closeSearch,
            )
        }
    ) {
        if (viewState.insertJobList is Fail) {
            FailJobListRequest(actioner)
        }
        when (viewState.JobList) {

            is Loading, Uninitialized -> {
                LoadingShimmerJobList()
            }
            is Fail -> {
                FailJobListRequest(actioner)
            }
            is Success -> {
                val jobList = viewState.JobList.invoke() ?: listOf()
                if (!closeSearchValue) {
                    SearchJobsList(viewState.searchJobList, Modifier, navController)
                } else {
                    SwipeRefresh(
                        modifier = Modifier.fillMaxSize(),
                        state = swipeRefreshState,
                        indicator = { state, trigger ->
                            SwipeRefreshIndicator(
                                state = state,
                                refreshTriggerDistance = trigger,
                                scale = true,
                                backgroundColor = MaterialTheme.colors.background,
                            )
                        },
                        onRefresh = {
                                actioner(JobScreenUiEvent.RefreshJobList)
                        }
                    ) {
                        LazyColumn(
                            modifier = modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colors.background)
                                .padding(top = dimensionResource(id = BaseR.dimen.spacing_2x)),
                            state = lazyListState

                        ) {
                            items(items = jobList, key = { it.id }) { item ->
                                JobItem(
                                    jobDto = item,
                                    modifier = Modifier,
                                    false,
                                    navController
                                )
                            }
                            if (viewState.insertJobList is LoadingMore) {
                                item {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(dimensionResource(id = BaseR.dimen.spacing_4x))
                                    ) {
                                        CircularProgressIndicator(
                                            modifier = Modifier
                                                .size(dimensionResource(id = BaseR.dimen.spacing_4x))
                                                .align(Alignment.Center),
                                            color = MaterialTheme.colors.primary,
                                            strokeWidth = dimensionResource(id = BaseR.dimen.spacing_half_base)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            else -> {}
        }
    }
    lazyListState.OnBottomReached {
        if (viewState.JobList !is Loading) {
            actioner(JobScreenUiEvent.ShowNextPage)
        }
    }
}

@Composable
fun LoadingShimmerJobList() {
    val jobList = listOf(
        JobDto(),
        JobDto(),
        JobDto(),
        JobDto(),
        JobDto()
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(top = dimensionResource(id = BaseR.dimen.spacing_2x)),

        ) {

        items(jobList) { item ->
            JobItem(
                jobDto = item,
                modifier = Modifier,
                isLoading = true
            )

        }
    }
}

@Composable
fun SearchJobsList(
    searchResultList: List<JobDto>,
    modifier: Modifier,
    navController: NavHostController
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(top = dimensionResource(id = BaseR.dimen.spacing_2x)),

        ) {
        items(searchResultList) { item ->
            JobItem(
                jobDto = item,
                modifier = Modifier,
                false,
                navController
            )
        }
    }
}

@Composable
fun FailJobListRequest(
    actioner: (JobScreenUiEvent) -> Unit
) {
    val openDialog = remember { mutableStateOf(false) }

    Column {
        LaunchedEffect(key1 = Unit) {
            openDialog.value = true
        }
        AlertDialogSample(
            openDialog.value, { openDialog.value = false },
            {
                actioner(JobScreenUiEvent.ShowAllJobList)
            },
        )
    }
}