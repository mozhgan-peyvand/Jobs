package com.example.ui.jobs.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.base.Fail
import com.example.base.Loading
import com.example.base.R
import com.example.base.Success
import com.example.base.util.toolbar.CollapsingToolbarScaffold
import com.example.base.util.toolbar.ScrollStrategy
import com.example.base.util.toolbar.rememberCollapsingToolbarScaffoldState
import com.example.common.ui.view.theme.h3Primary
import com.example.ui.jobs.models.JobInfoModel
import com.example.ui.jobs.models.JobScreenState
import com.example.ui.jobs.models.JobScreenUiEvent
import com.example.ui.jobs.models.toViewJob
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Composable
fun JobScreen(
    viewModel: JobViewModel = hiltViewModel(),
    navigateUp : () -> Unit
) {

    val viewState by viewModel.stateFlow.collectAsState(initial = JobScreenState())
    JobScreenList(
        actioner = { action ->
            viewModel.submitAction(action)
        },
        viewState,
        navigateUp
    )
}

@Composable
fun JobScreenList(
    actioner: (JobScreenUiEvent) -> Unit,
    viewState: JobScreenState,
    navigateUp: () -> Unit
) {
    val filterResultList = remember {
        mutableStateListOf<String>()
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

        JobList(
            Modifier.padding(paddingValues),
            onMenuClicked = {
                coroutineScope.launch {
                    scaffoldState.drawerState.open()
                }
            },
            filterResultList,
            viewState,
            navigateUp
        )
    }
}

@Composable
private fun JobList(
    modifier: Modifier,
    onMenuClicked: () -> Job,
    filterResultList: SnapshotStateList<String>,
    viewState: JobScreenState,
    navigateUp: () -> Unit,
) {
    val state = rememberCollapsingToolbarScaffoldState()
    var openDialog = remember { mutableStateOf(false) }


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
        when (viewState.allJobList) {
            is Success -> {
                var jobList = viewState.allJobList.invoke()?.map { it.toViewJob() } ?: listOf()
                if (jobList.isNotEmpty()) {
                    LazyColumn(
                        modifier = modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.background)
                            .padding(top = dimensionResource(id = R.dimen.spacing_2x)),

                        ) {

                        items(jobList) { item ->
                            JobItem(
                                item = item,
                                modifier = Modifier,
                                false
                            )

                        }
                    }
                } else {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "There are no search results !",
                            modifier = Modifier.padding(dimensionResource(id = R.dimen.spacing_2x)),
                            style = MaterialTheme.typography.h3Primary()
                        )
                    }
                }

            }
            is Loading -> {
                LoadingShimmerJobList()
            }
            is Fail -> {
                Column {
                    AlertDialogSample(navigateUp)
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
            .padding(top = dimensionResource(id = R.dimen.spacing_2x)),

        ) {

        items(jobList) { item ->
            JobItem(
                isloading = true,
                item = item,
                modifier = Modifier
            )

        }
    }
}

@Composable
fun AlertDialogSample(navigateUp: () -> Unit) {
        AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onCloseRequest.
                               navigateUp.invoke()
            },
            title = {
                Text(text = "error!")
            },
            text = {
                Text("Error processing data. Please try again later")
            },
            confirmButton = {
                Button(

                    onClick = {

                    }) {
                    Text("retry")
                }
            },
            dismissButton = {
                Button(

                    onClick = {
                        navigateUp.invoke()
                    }) {
                    Text("dismiss")
                }
            }
        )
}
