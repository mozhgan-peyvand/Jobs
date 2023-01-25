package com.example.data_android

import com.example.data.jobs.di.ApiUrlHelper
import com.example.data.jobs.models.GetJobResponse
import com.example.data.jobs.repositories.JobRemoteDataSource
import com.example.data.jobs.repositories.JobService
import com.example.data_android.util.RequestHandler
import javax.inject.Inject

class JobRemoteDataSourceImp @Inject constructor(
    private val service: JobService,
    private val requestHandler: RequestHandler
) : JobRemoteDataSource {

    override suspend fun getAllJobList(): List<GetJobResponse>? {
        return requestHandler.getRequestBodyOrThrow(
            service.getAllJobs(url = ApiUrlHelper.API_URL)
        ).results
    }

    override suspend fun getFilterJobList(role: String?, city: String?): List<GetJobResponse>? {
        return requestHandler.getRequestBodyOrThrow(
            service.searchJobs(
                url = ApiUrlHelper.API_URL,
                location = city,
                search = role
            )
        ).results
    }
}