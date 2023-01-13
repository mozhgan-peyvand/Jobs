package com.example.ui_jobs.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.base.R
import com.example.common_ui_view.JobInfoModel

@Composable
fun JobScreen() {
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
        JobList(jobList)
}

@Composable
private fun JobList(
    items: List<JobInfoModel>
) {
    val state: LazyListState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
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

