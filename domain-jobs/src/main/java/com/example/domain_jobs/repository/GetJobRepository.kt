package com.example.domain_jobs.repository

import com.example.base.models.JobDto
import kotlinx.coroutines.flow.Flow

interface GetJobRepository {
    suspend fun insertAllJobs(page: Int): Unit
    suspend fun getAllRoles(): Flow<List<String>>
    suspend fun getAllLocation(): Flow<List<String>>
    suspend fun getAllJobs(): Flow<List<JobDto>?>
    suspend fun filterJobsList(role: String? , city: String?): Flow<List<JobDto>?>
}