package com.example.ui.jobs.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.base.*
import com.example.base.util.*
import com.example.base.util.toolbar.CollapsingToolbarScaffold
import com.example.base.util.toolbar.ScrollStrategy
import com.example.base.util.toolbar.rememberCollapsingToolbarScaffoldState
import com.example.ui.jobs.models.JobScreenState
import com.example.ui.jobs.models.JobScreenUiEvent
import com.example.ui.jobs.util.ui.AlertDialogSample
import com.example.ui.jobs.util.ui.EmptyJobList
import com.example.ui.jobs.util.ui.OnBottomReached
import com.example.ui.jobs.util.ui.rememberMutableStateListOf
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import com.example.base.R as BaseR


@Composable
fun JobScreen(
    viewModel: JobViewModel
) {

    val viewState by viewModel.stateFlow.collectAsState(initial = JobScreenState())
    JobScreenList(
        actioner = { action ->
            viewModel.submitAction(action)
        },
        viewState = viewState
    )
}

@Composable
fun JobScreenList(
    actioner: (JobScreenUiEvent) -> Unit,
    viewState: JobScreenState,
) {

    val closeSearch = rememberSaveable {
        mutableStateOf(true)
    }
    val searchText = rememberSaveable {
        mutableStateOf("")
    }
    val filterResultList = rememberMutableStateListOf("", "")

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            FilterJobs(
                onCloseFilterJobsDrawer = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.close()
                    }
                },
                filterJobList = { role, city ->
                    actioner(
                        JobScreenUiEvent.FilterJobsList(role, city)
                    )
                },
                viewState = viewState,
                filterResultList = filterResultList,
                closeSearch = { closeSearch.value = it }
            ) { searchText.value = it }
        }
    ) { paddingValues ->

        JobList(
            modifier = Modifier.padding(paddingValues),
            onMenuClicked = {
                coroutineScope.launch {
                    scaffoldState.drawerState.open()
                }
            },
            filterResultList = filterResultList,
            viewState = viewState,
            closeSearch = { closeSearch.value = it },
            closeSearchValue = closeSearch.value,
            searchText = searchText.value,
            onChangeSearchText = { searchText.value = it },
            actioner = actioner
        )
    }
}

@Composable
private fun JobList(
    modifier: Modifier,
    onMenuClicked: () -> Job,
    filterResultList: SnapshotStateList<String>,
    viewState: JobScreenState,
    closeSearch: (Boolean) -> Unit,
    closeSearchValue: Boolean,
    searchText: String,
    onChangeSearchText: (String) -> Unit,
    actioner: (JobScreenUiEvent) -> Unit,
) {
    val state = rememberCollapsingToolbarScaffoldState()
    val openDialog = remember { mutableStateOf(false) }
    val lazyListState = rememberLazyListState()
    val swipeRefreshState = rememberSwipeRefreshState(false)

    CollapsingToolbarScaffold(
        modifier = Modifier
            .fillMaxSize(),
        state = state,
        scrollStrategy = ScrollStrategy.EnterAlways,
        toolbar = {
            JobTopBar(
                onClickedFilterJobs = {
                    onMenuClicked.invoke()
                },
                filterResultList = filterResultList,
                actioner = { searchText -> actioner(JobScreenUiEvent.SearchJobsList(searchText)) },
                closeSearch = closeSearch,
                searchText = searchText,
                onChangeSearchText = onChangeSearchText
            )
        }
    ) {
        when (viewState.allJobList) {
            is Loading,Uninitialized -> {
                LoadingShimmerJobList()
            }
            is Fail -> {
                Column {
                    LaunchedEffect(key1 = Unit) {
                        openDialog.value = true
                    }
                    AlertDialogSample(
                        openDialog.value, { openDialog.value = false },
                        {
                            if (filterResultList.filter { it.isEmpty() }.size == 2)
                                actioner(JobScreenUiEvent.ShowAllJobList)
                            else
                                actioner(
                                    JobScreenUiEvent.FilterJobsList(
                                        filterResultList[0],
                                        filterResultList[1]
                                    )
                                )
                        },
                    )
                }

            }
            else -> {
                val jobList = viewState.allJobList.invoke() ?: listOf()
                if (!closeSearchValue) {
                    LazyColumn(
                        modifier = modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.background)
                            .padding(top = dimensionResource(id = BaseR.dimen.spacing_2x)),

                        ) {
                        items(viewState.searchResultList) { item ->
                            JobItem(
                                jobInfoView = item,
                                modifier = Modifier,
                                false
                            )
                        }
                    }
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
                        onRefresh = { actioner(JobScreenUiEvent.RefreshJobList) }
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
                                    jobInfoView = item,
                                    modifier = Modifier,
                                    false
                                )
                            }
                            if (viewState.insertJobList is LoadingMore || viewState.allJobList is LoadingMore) {
                                item {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp)
                                    ) {
                                        CircularProgressIndicator(
                                            modifier = Modifier
                                                .size(16.dp)
                                                .align(Alignment.Center),
                                            color = MaterialTheme.colors.primary,
                                            strokeWidth = 2.dp
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        lazyListState.OnBottomReached {
            if (viewState.allJobList !is Loading && filterResultList.filter { it.isEmpty() }.size == 2 && searchText.isEmpty()) {
                actioner(JobScreenUiEvent.ShowNextPage)
            }
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
                isLoading = true,
                jobInfoView = item,
                modifier = Modifier
            )

        }
    }
}

