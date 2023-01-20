package com.example.data.jobs.repositories

import com.example.base.api.BaseRemoteDataSource
import com.example.base.api.safeApiCall
import com.example.data.jobs.di.ApiUrlHelper
import javax.inject.Inject

class GetJobRemoteDataSource @Inject constructor(
    private val service: JobService
): BaseRemoteDataSource() {
    suspend fun getJobList() = safeApiCall(
        call = { requestGetJobList() },
        errorMessage = "Error get article list"
    )

    private suspend fun requestGetJobList() = checkApiResult(
        service.getAllJobs(
            url = ApiUrlHelper.API_URL,
        )
    )

}