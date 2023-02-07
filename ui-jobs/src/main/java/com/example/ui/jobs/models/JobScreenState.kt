package com.example.ui.jobs.models

import com.example.base.util.AsyncResult
import com.example.base.models.JobDto
import com.example.base.util.Uninitialized
import javax.annotation.concurrent.Immutable

@Immutable
data class JobScreenState(
    val JobList: AsyncResult<List<JobDto>?> = Uninitialized,
    val insertJobList: AsyncResult<Unit> = Uninitialized,
    val LocationList: AsyncResult<List<String>> = Uninitialized,
    val RoleList: AsyncResult<List<String>> = Uninitialized,
    val searchJobList: List<JobDto> = emptyList()
)