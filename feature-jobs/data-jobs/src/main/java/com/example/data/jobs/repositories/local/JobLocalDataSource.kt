package com.example.data.jobs.repositories.local

import com.example.base.models.JobDto
import kotlinx.coroutines.flow.Flow

interface JobLocalDataSource {
    suspend fun insertJobList(jobList: List<JobDto>, page:Int): Unit
    suspend fun getAllJobList(): Flow<List<JobDto>?>
    suspend fun getJobDetailInfo(jobId: String): Flow<JobDto>
}