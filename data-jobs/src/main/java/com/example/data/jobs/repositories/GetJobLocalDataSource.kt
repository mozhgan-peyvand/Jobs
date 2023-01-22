package com.example.data.jobs.repositories

import androidx.room.withTransaction
import com.example.data.jobs.db.AppDataBase
import com.example.data.jobs.models.GetJobDTO
import com.example.domain_jobs.model.GetJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetJobLocalDataSource @Inject constructor(
    private val jobDao: JobDao,
    private val appDataBase: AppDataBase
) {

    fun getJobList(): Flow<List<GetJob>> {
        return jobDao.getJobList().map { jobList ->
            jobList?.map { jobDto ->
                jobDto.toGetJob()
            } ?: emptyList()
        }
    }
    suspend fun insertArticleList(
        jobList: List<GetJobDTO>
    ) {
        appDataBase.withTransaction {
            jobDao.insertJobList(jobList)
        }
    }

    fun getLocationList() : Flow<List<String>> {
        return jobDao.getLocationList()
    }

    fun getRolesList() : Flow<List<String>> {
        return jobDao.getRoleList()
    }
}