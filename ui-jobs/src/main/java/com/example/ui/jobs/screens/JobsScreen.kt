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
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Composable
fun JobScreen(
    viewModel: JobViewModel = hiltViewModel()
) {

    val filterResultList = remember {
        mutableStateListOf<String>()
    }

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    val coroutineScope = rememberCoroutineScope()
    val isloading = viewModel.jobList.value.isLoading
    val jobList = viewModel.jobList.value.data ?: listOf()
    val locationList = viewModel.locationList.collectAsState(initial = listOf())
    val roleList = viewModel.rolesList.collectAsState(initial = listOf())

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            FilterJobs(
                {
                    coroutineScope.launch {
                        scaffoldState.drawerState.close()
                    }
                },
                { filterResultList.addAll(it) },
                { filterResultList.clear() },
                { role, city -> viewModel.filterJobs(role = role, city = city) },
                locationList = locationList.value,
                roleList = roleList.value
            )
        }
    ) { paddingValues ->
        JobList(
            items = jobList, Modifier.padding(paddingValues),
            onMenuClicked = {
                coroutineScope.launch {
                    scaffoldState.drawerState.open()
                }
            },
            filterResultList,
            { filterResultList.remove(it) },
            isloading

        ) { role, city -> viewModel.filterJobs(role = role, city = city) }
    }
}

@Composable
private fun JobList(
    items: List<JobInfoModel>,
    modifier: Modifier,
    onMenuClicked: () -> Job,
    filterResultList: SnapshotStateList<String>,
    function: (String) -> Boolean,
    isloading: Boolean,
    filterJobsList: (String?, String?) -> Unit,

    ) {
    val state = rememberCollapsingToolbarScaffoldState()


    CollapsingToolbarScaffold(
        modifier = Modifier
            .fillMaxSize(),
        state = state,
        scrollStrategy = ScrollStrategy.EnterAlways,
        toolbar = {
            JobTopBar(
                onMenuClicked = {
                    onMenuClicked.invoke()
                },
                filterResultList,
                { function.invoke(it) },
                filterJobsListRequest = { role, city -> filterJobsList(role, city) }
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

