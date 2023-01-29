package com.example.data_android.dataSourceImp

import com.example.base.api.ApiUrlHelper
import com.example.data.jobs.models.JobResponse
import com.example.data.jobs.repositories.JobRemoteDataSource
import com.example.data.jobs.repositories.JobService
import com.example.base.api.RequestHandler
import javax.inject.Inject

class JobRemoteDataSourceImp @Inject constructor(
    private val service: JobService,
    private val requestHandler: RequestHandler
) : JobRemoteDataSource {

    override suspend fun getAllJobList(): List<JobResponse>? {
        return requestHandler.getRequestBodyOrThrow(
            service.getAllJobs(url = ApiUrlHelper.API_URL)
        ).results
    }

    override suspend fun getFilterJobList(role: String?, city: String?): List<JobResponse>? {
        return requestHandler.getRequestBodyOrThrow(
            service.searchJobs(
                url = ApiUrlHelper.API_URL,
                location = city,
                search = role
            )
        ).results
    }
}