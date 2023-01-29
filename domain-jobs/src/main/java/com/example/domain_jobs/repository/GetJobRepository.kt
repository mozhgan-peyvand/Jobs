package com.example.domain_jobs.repository

import com.example.domain_jobs.model.JobModel
import kotlinx.coroutines.flow.Flow

interface GetJobRepository {
    suspend fun getAllRoles(): Flow<List<String>>
    suspend fun getAllLocation() : Flow<List<String>>
    suspend fun getAllJobs() :List<JobModel>?
    suspend fun filterJobsList(role: String? , city: String?) :List<JobModel>?
}