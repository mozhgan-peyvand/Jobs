package com.example.ui.jobs.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.base.R
import com.example.common.ui.view.JobInfoModel
import kotlinx.coroutines.launch

@Composable
fun JobScreen(changeTheme: () -> Unit) {

    var filterResultList = remember {
        mutableStateListOf<String>()
    }

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    val coroutineScope = rememberCoroutineScope()
    var jobList = listOf(
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
        topBar = {
            TopBar(
                // When menu is clicked open the
                // drawer in coroutine scope
                onMenuClicked = {
                    coroutineScope.launch {
                        // to close use -> scaffoldState.drawerState.close()
                        scaffoldState.drawerState.open()
                    }
                },
                filterResultList,
                { filterResultList.remove(it) },
                changeTheme = changeTheme
            )
        },
        drawerContent = {
            FilterJobs(
                {
                    coroutineScope.launch {
                        // to close use -> scaffoldState.drawerState.close()
                        scaffoldState.drawerState.close()
                    }
                },
                { filterResultList.addAll(it) },
                { filterResultList.clear() }
            )
        }
    ) { paddingValues ->
        JobList(items = jobList, Modifier.padding(paddingValues))
    }
}

@Composable
private fun JobList(
    items: List<JobInfoModel>,
    modifier: Modifier
) {
    val state: LazyListState = rememberLazyListState()
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color(236, 234, 235))
            .padding(top = dimensionResource(id = R.dimen.spacing_2x)),
        state = state,
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

