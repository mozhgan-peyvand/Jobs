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
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
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
        viewState = viewState,
        actioner = { action ->
            viewModel.submitAction(action)
        }
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
                        JobScreenUiEvent.FilterJobsList(
                            role,
                            city
                        )
                    )
                },
                viewState = viewState,
                filterResultList = filterResultList,
                closeSearch = { closeSearch.value = it },
                onChangeSearchText = { searchText.value = it }
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
            actioner = actioner,
            closeSearch = { closeSearch.value = it },
            closeSearchValue = closeSearch.value,
            searchText = searchText.value,
            onChangeSearchText = { searchText.value = it },
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
            is Success -> {
                val jobList = viewState.allJobList.invoke()?.map { it.toViewJob() } ?: listOf()
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
                    AlertDialogSample(
                        openDialog.value, { openDialog.value = false },
                        {
                            if (filterResultList.isEmpty())
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

        }
    }
}

@Composable
fun LoadingShimmerJobList() {
    val jobList = listOf(
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

@Composable
fun <T : Any> rememberMutableStateListOf(vararg elements: T): SnapshotStateList<T> {
    return rememberSaveable(
        saver = listSaver(
            save = { stateList ->
                if (stateList.isNotEmpty()) {
                    val first = stateList.first()
                    if (!canBeSaved(first)) {
                        throw IllegalStateException("${first::class} cannot be saved. By default only types which can be stored in the Bundle class can be saved.")
                    }
                }
                stateList.toList()
            },
            restore = { it.toMutableStateList() }
        )
    ) {
        elements.toList().toMutableStateList()
    }
}