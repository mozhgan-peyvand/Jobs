package com.example.ui.jobs.models

import com.example.base.util.AsyncResult
import com.example.base.JobDto
import com.example.base.util.Uninitialized
import javax.annotation.concurrent.Immutable

@Immutable
data class JobScreenState(
    val allJobList: AsyncResult<List<JobDto>?> = Uninitialized,
    val insertJobList: AsyncResult<Unit> = Uninitialized,
    val allLocationList: AsyncResult<List<String>> = Uninitialized,
    val allRoleList: AsyncResult<List<String>> = Uninitialized,
    val searchResultList: List<JobDto> = emptyList()
)