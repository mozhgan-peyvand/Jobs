package com.example.ui.jobs.models

import com.example.base.AsyncResult
import com.example.base.Uninitialized
import com.example.domain_jobs.model.JobModel
import kotlinx.coroutines.flow.Flow
import javax.annotation.concurrent.Immutable

@Immutable
data class JobScreenState(
    val allJobList: AsyncResult<List<JobModel>?> = Uninitialized,
    val allLocationList: AsyncResult<List<String>> = Uninitialized,
    val allRoleList: AsyncResult<List<String>> = Uninitialized,
    val searchResultList: List<JobInfoModel> = emptyList()
)