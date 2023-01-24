package com.example.ui.jobs.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.base.R
import com.example.base.util.toolbar.CollapsingToolbarScaffold
import com.example.base.util.toolbar.ScrollStrategy
import com.example.base.util.toolbar.rememberCollapsingToolbarScaffoldState
import com.example.ui.jobs.models.JobInfoModel
import com.example.ui.jobs.models.JobScreenState
import com.example.ui.jobs.models.JobScreenUiEvent
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Composable
fun JobScreen(
    viewModel: JobViewModel = hiltViewModel()
) {

    val viewState by viewModel.stateFlow.collectAsState(initial = JobScreenState())

    JobScreenList(
        isloading = false,
        actioner = { action ->
            viewModel.submitAction(action)
        },
        viewState
    )

}

@Composable
fun JobScreenList(
    isloading: Boolean,
    actioner: (JobScreenUiEvent) -> Unit,
    viewState: JobScreenState
) {
    val filterResultList = remember {
        mutableStateListOf<String>()
    }

    val jobList = remember {
        mutableStateOf<List<JobInfoModel>>(listOf())
    }
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
                addFilterResultJob = { filterResultList.addAll(it) },
                clearFilterResultJob = { filterResultList.clear() },
                filterJobList = { role, city ->
                    actioner(
                        JobScreenUiEvent.FilterJobsList(
                            role,
                            city
                        )
                    )
                },
                viewState
            )
        }
    ) { paddingValues ->

        viewState.allJobList.handleEvent(
            onSuccess = {
                jobList.value = it ?: listOf()
            }
        )

        JobList(
            items = jobList.value, Modifier.padding(paddingValues),
            onMenuClicked = {
                coroutineScope.launch {
                    scaffoldState.drawerState.open()
                }
            },
            filterResultList,
            isloading
        )
    }
}

@Composable
private fun JobList(
    items: List<JobInfoModel>,
    modifier: Modifier,
    onMenuClicked: () -> Job,
    filterResultList: SnapshotStateList<String>,
    isloading: Boolean,

    ) {
    val state = rememberCollapsingToolbarScaffoldState()


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
                filterResultList = filterResultList
            )
        }
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(top = dimensionResource(id = R.dimen.spacing_2x)),

            ) {
            items(items) { item ->
                JobItem(
                    item = item,
                    modifier = Modifier,
                    isloading
                )

            }
        }
    }
}

