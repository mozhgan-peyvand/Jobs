package com.example.ui.jobs.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.base.Fail
import com.example.base.Loading
import com.example.base.Success
import com.example.base.util.toolbar.CollapsingToolbarScaffold
import com.example.base.util.toolbar.ScrollStrategy
import com.example.base.util.toolbar.rememberCollapsingToolbarScaffoldState
import com.example.common.ui.view.theme.captionOnPrimary
import com.example.common.ui.view.theme.captionOnSurface
import com.example.common.ui.view.theme.h3Primary
import com.example.ui.jobs.models.JobInfoModel
import com.example.ui.jobs.models.JobScreenState
import com.example.ui.jobs.models.JobScreenUiEvent
import com.example.ui.jobs.models.toViewJob
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import com.example.base.R as BaseR
import com.example.ui.jobs.R as UiJobsR


@Composable
fun JobScreen(
    viewModel: JobViewModel = hiltViewModel()
) {

    val viewState by viewModel.stateFlow.collectAsState(initial = JobScreenState())
    JobScreenList(
        actioner = { action ->
            viewModel.submitAction(action)
        },
        viewState = viewState,
        filterResultList = viewModel.filterResultList,
        searchResultList = viewModel.searchResultList,
        searchText = viewModel.searchText,
        onChangeSearchText = { viewModel.searchText.value = it },
        closeSearch = viewModel.closeSearch,
        onCloseSearch = { viewModel.closeSearch.value = it }
    )
}

@Composable
fun JobScreenList(
    actioner: (JobScreenUiEvent) -> Unit,
    viewState: JobScreenState,
    filterResultList: SnapshotStateList<String>,
    searchResultList: SnapshotStateList<JobInfoModel>,
    searchText: MutableState<String>,
    onChangeSearchText: (String) -> Unit,
    onCloseSearch: (Boolean) -> Unit,
    closeSearch: MutableState<Boolean>,
) {
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
                        JobScreenUiEvent.FilterJobsList(
                            role,
                            city
                        )
                    )
                },
                viewState = viewState,
                filterResultList = filterResultList,
                closeSearch = onCloseSearch,
                onChangeSearchText = onChangeSearchText
            )
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
            actioner = {
                if (filterResultList.isEmpty())
                    actioner(JobScreenUiEvent.ShowAllJobList)
                else
                    actioner(
                        JobScreenUiEvent.FilterJobsList(filterResultList[0], filterResultList[1])
                    )
            },
            searchResultList = searchResultList,
            closeSearch = onCloseSearch,
            closeSearchValue = closeSearch.value,
            searchText = searchText.value,
            onChangeSearchText = onChangeSearchText
        )
    }
}

@Composable
private fun JobList(
    modifier: Modifier,
    onMenuClicked: () -> Job,
    filterResultList: SnapshotStateList<String>,
    viewState: JobScreenState,
    actioner: () -> Unit,
    searchResultList: SnapshotStateList<JobInfoModel>,
    closeSearch: (Boolean) -> Unit,
    closeSearchValue: Boolean,
    searchText: String,
    onChangeSearchText: (String) -> Unit,
) {
    val state = rememberCollapsingToolbarScaffoldState()
    val openDialog = remember { mutableStateOf(false) }

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
                searchResultList = searchResultList,
                viewState = viewState,
                closeSearch = closeSearch,
                searchText = searchText,
                onChangeSearchText = onChangeSearchText
            )
        }
    ) {
        when (viewState.allJobList) {
            is Success -> {
                var jobList = viewState.allJobList.invoke()?.map { it.toViewJob() } ?: listOf()
                if (!closeSearchValue) {
                    LazyColumn(
                        modifier = modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.background)
                            .padding(top = dimensionResource(id = BaseR.dimen.spacing_2x)),

                        ) {
                        items(searchResultList) { item ->
                            JobItem(
                                jobInfoView = item,
                                modifier = Modifier,
                                false
                            )
                        }
                    }
                } else if (jobList.isEmpty()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(id = UiJobsR.string.msg_empty_result_job_search),
                            modifier = Modifier.padding(dimensionResource(id = BaseR.dimen.spacing_2x)),
                            style = MaterialTheme.typography.h3Primary()
                        )
                    }

                } else {

                    LazyColumn(
                        modifier = modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.background)
                            .padding(top = dimensionResource(id = BaseR.dimen.spacing_2x)),

                        ) {

                        items(jobList) { item ->
                            JobItem(
                                jobInfoView = item,
                                modifier = Modifier,
                                false
                            )
                        }
                    }
                }

            }
            is Loading -> {
                LoadingShimmerJobList()
            }
            is Fail -> {
                Column {
                    LaunchedEffect(key1 = Unit) {
                        openDialog.value = true
                    }
                    AlertDialogSample(openDialog.value, { openDialog.value = false }, actioner)
                }

            }

        }
    }
}

@Composable
fun LoadingShimmerJobList() {
    var jobList = listOf(
        JobInfoModel(),
        JobInfoModel(),
        JobInfoModel(),
        JobInfoModel(),
        JobInfoModel(),
        JobInfoModel(),
        JobInfoModel(),
        JobInfoModel()
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(top = dimensionResource(id = BaseR.dimen.spacing_2x)),

        ) {

        items(jobList) { item ->
            JobItem(
                isloading = true,
                jobInfoView = item,
                modifier = Modifier
            )

        }
    }
}

@Composable
fun AlertDialogSample(value: Boolean, function: () -> Unit, actioner: () -> Unit) {
    if (value) {
        AlertDialog(
            onDismissRequest = {
                function.invoke()
            },
            title = {
                Text(
                    text = stringResource(id = UiJobsR.string.label_title_error_dialog),
                    style = MaterialTheme.typography.h3Primary()
                )
            },
            text = {
                Text(
                    stringResource(id = UiJobsR.string.msg_error_dialog),
                    style = MaterialTheme.typography.captionOnSurface()
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        actioner.invoke()
                    }) {
                    Text(
                        stringResource(id = UiJobsR.string.label_dialog_retry),
                        style = MaterialTheme.typography.captionOnPrimary()
                    )
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        function.invoke()
                    }) {
                    Text(
                        stringResource(id = UiJobsR.string.label_dialog_dismiss),
                        style = MaterialTheme.typography.captionOnPrimary()
                    )
                }
            }
        )
    }
}
