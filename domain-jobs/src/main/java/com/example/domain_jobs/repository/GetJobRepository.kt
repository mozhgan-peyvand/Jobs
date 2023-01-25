package com.example.domain_jobs.repository

import com.example.base.api.Resource
import com.example.domain_jobs.model.GetJob
import kotlinx.coroutines.flow.Flow

interface GetJobRepository {
    suspend fun getAllRoles(): Flow<List<String>>
    suspend fun getAllLocation() : Flow<List<String>>
    suspend fun getAllJobs() :List<GetJob>?
    suspend fun filterJobsList(role: String? , city: String?) :List<GetJob>?
}