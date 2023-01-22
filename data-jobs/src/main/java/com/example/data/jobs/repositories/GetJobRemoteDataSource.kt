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

    suspend fun filterJobList(role: String? ,city: String? ) = safeApiCall(
        call = {requestSearchJobs(role,city)},
        errorMessage = "Error get Filter job list"
    )

    private suspend fun requestSearchJobs(role: String? , city: String? ) = checkApiResult(
        service.searchJobs(
            url = ApiUrlHelper.API_URL,
            location = city,
            search = role
        )
    )

}