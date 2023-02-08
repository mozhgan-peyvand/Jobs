package com.example.data.jobs.repositories.remote

import com.example.data.jobs.models.JobResponse

interface JobRemoteDataSource {
    suspend fun getAllJobList(page: Int) : List<JobResponse>?
}