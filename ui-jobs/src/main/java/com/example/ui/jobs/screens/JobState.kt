package com.example.ui.jobs.screens

import com.example.ui.jobs.models.JobInfoModel

data class JobState(
    val data: List<JobInfoModel>? = null,
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val errorMessage: String? = null
)