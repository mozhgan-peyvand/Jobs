package com.example.ui_jobs.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ui_jobs.JobCard
import com.example.ui_jobs.JobInfoModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun JobScreen() {

    var jobList = listOf(
        JobInfoModel(1,"android","description",1),
        JobInfoModel(2,"android","description",1),
        JobInfoModel(3,"android","description",1),
        JobInfoModel(4,"android","description",1),
        JobInfoModel(5,"android","description",1),
        JobInfoModel(6,"android","description",1),
        JobInfoModel(7,"android","description",1),

    )
    var jobStateList by remember {
        mutableStateOf(jobList)
    }
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 25.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    " Plants in Cosmetics",
                    style = MaterialTheme.typography.h3
                )
            }
        }
        items(items = jobStateList, key = { it.id}){ jobInfo ->
            Row(modifier = Modifier.padding(1.dp).animateItemPlacement()) {

                JobCard(jobInfo.name, jobInfo.description, jobInfo.imageRes)
            }
        }
    }


}