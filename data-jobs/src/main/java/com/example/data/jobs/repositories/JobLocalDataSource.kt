package com.example.data.jobs.repositories

import com.example.data.jobs.models.JobDto
import kotlinx.coroutines.flow.Flow

interface JobLocalDataSource {
    suspend fun insertJobList(jobList: List<JobDto>): Unit
    suspend fun getLocationList(): Flow<List<String>>
    suspend fun getRoleList(): Flow<List<String>>
}