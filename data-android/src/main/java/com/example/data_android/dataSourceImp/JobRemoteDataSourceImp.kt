package com.example.data_android.dataSourceImp

import com.example.base.api.ApiUrlHelper
import com.example.data.jobs.models.JobResponse
import com.example.data.jobs.repositories.remote.JobRemoteDataSource
import com.example.data.jobs.repositories.remote.JobService
import com.example.base.api.RequestHandler
import javax.inject.Inject

class JobRemoteDataSourceImp @Inject constructor(
    private val service: JobService,
    private val requestHandler: RequestHandler
) : JobRemoteDataSource {

    override suspend fun getAllJobList(page: Int): List<JobResponse>? {
        return requestHandler.getRequestBodyOrThrow(
            service.getAllJobs(url = ApiUrlHelper.API_URL,page = page)
        ).results
    }
}