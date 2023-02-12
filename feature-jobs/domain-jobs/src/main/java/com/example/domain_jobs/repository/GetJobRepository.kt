package com.example.domain_jobs.repository

import com.example.base.models.JobDto
import kotlinx.coroutines.flow.Flow

interface GetJobRepository {
    suspend fun insertJobList(page: Int): Unit
    suspend fun getJobList(): Flow<List<JobDto>?>
    suspend fun getJobDetailInfo(jobId: String): Flow<JobDto>
}