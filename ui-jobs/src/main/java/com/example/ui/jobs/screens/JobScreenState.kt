package com.example.ui.jobs.screens

import com.example.ui.jobs.models.JobInfoModel

data class JobScreenState(
    val data: List<JobInfoModel>? = null,
    val isLoading: Boolean = true,
    val hasError: Boolean = false,
    val errorMessage: String? = null
)