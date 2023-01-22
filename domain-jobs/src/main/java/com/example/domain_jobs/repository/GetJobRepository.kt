package com.example.domain_jobs.repository

import com.example.base.api.Resource
import com.example.domain_jobs.model.GetJob
import kotlinx.coroutines.flow.Flow

interface GetJobRepository {
    fun getAllRoles(): Flow<List<String>>
    fun getAllLocation() : Flow<List<String>>
    suspend fun getAllJobs() : Flow<Resource<List<GetJob>?>>
    suspend fun filterJobsList(role: String? , city: String?) : Flow<Resource<List<GetJob>?>>
}