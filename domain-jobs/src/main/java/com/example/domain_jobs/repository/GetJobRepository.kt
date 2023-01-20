package com.example.domain_jobs.repository

import com.example.base.api.Resource
import com.example.domain_jobs.model.GetJob
import kotlinx.coroutines.flow.Flow

interface GetJobRepository {
    suspend fun getAllJobs() : Flow<Resource<List<GetJob>?>>
}