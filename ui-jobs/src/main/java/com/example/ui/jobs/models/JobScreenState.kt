package com.example.ui.jobs.models

import com.example.base.AsyncResult
import com.example.base.Uninitialized
import javax.annotation.concurrent.Immutable

@Immutable
 data class JobScreenState(
    val allJobList: AsyncResult<List<JobInfoModel>?> = Uninitialized,
    val allLocationList: List<String> = listOf(),
    val allRoleList: List<String> = listOf(),
    val isLoading: Boolean = true,
    val hasError: Boolean = false,
    val errorMessage: String? = null
)