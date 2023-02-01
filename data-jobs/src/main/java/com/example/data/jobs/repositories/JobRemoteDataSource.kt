package com.example.data.jobs.repositories

import com.example.data.jobs.models.JobResponse

interface JobRemoteDataSource {
    suspend fun getAllJobList() : List<JobResponse>?
}