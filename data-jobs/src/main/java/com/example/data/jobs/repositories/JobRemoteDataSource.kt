package com.example.data.jobs.repositories

import com.example.data.jobs.models.GetJobResponse

interface JobRemoteDataSource {
    suspend fun getAllJobList() : List<GetJobResponse>?
    suspend fun getFilterJobList(role: String?,city: String?) : List<GetJobResponse>?
}