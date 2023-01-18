package com.example.ui.jobs.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.base.R
import com.example.base.util.toolbar.CollapsingToolbarScaffold
import com.example.base.util.toolbar.ScrollStrategy
import com.example.base.util.toolbar.rememberCollapsingToolbarScaffoldState
import com.example.ui.jobs.models.JobInfoModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Composable
fun JobScreen() {

    val filterResultList = remember {
        mutableStateListOf<String>()
    }

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    val coroutineScope = rememberCoroutineScope()
    val jobList = listOf(
        JobInfoModel(
            1,
            "part",
            "london",
            "part_time",
            "android_Developer",
            "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
        ),
        JobInfoModel(
            2,
            "part",
            "london",
            "part_time",
            "android_Developer",
            "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
        ),
        JobInfoModel(
            3,
            "part",
            "london",
            "part_time",
            "android_Developer",
            "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
        ),
        JobInfoModel(
            4,
            "part",
            "london",
            "part_time",
            "android_Developer",
            "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
        ),

        JobInfoModel(
            4,
            "part",
            "london",
            "part_time",
            "android_Developer",
            "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
        ),
        JobInfoModel(
            4,
            "part",
            "london",
            "part_time",
            "android_Developer",
            "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
        ),
        JobInfoModel(
            4,
            "part",
            "london",
            "part_time",
            "android_Developer",
            "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
        ),
    )
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
                { filterResultList.clear() }
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
            filterResultList


        ) { filterResultList.remove(it) }
    }
}

@Composable
private fun JobList(
    items: List<JobInfoModel>,
    modifier: Modifier,
    onMenuClicked: () -> Job,
    filterResultList: SnapshotStateList<String>,
    function: (String) -> Boolean,

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
                { function.invoke(it) }
            )
        }
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(top = dimensionResource(id = R.dimen.spacing_2x)),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(
                start = dimensionResource(id = R.dimen.spacing_4x),
                end = dimensionResource(id = R.dimen.spacing_4x),
                bottom = dimensionResource(id = R.dimen.spacing_4x)
            )
        ) {
            items(items) { item ->
                JobItem(
                    item = item,
                    modifier = Modifier,
                    imageModifier = Modifier
                )

            }
        }
    }
}

