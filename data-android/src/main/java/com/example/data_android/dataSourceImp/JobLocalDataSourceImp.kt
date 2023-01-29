package com.example.data_android.dataSourceImp

import androidx.room.withTransaction
import com.example.data.jobs.db.AppDataBase
import com.example.data.jobs.models.JobDto
import com.example.data.jobs.repositories.JobDao
import com.example.data.jobs.repositories.JobLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class JobLocalDataSourceImp @Inject constructor(
    private val jobDao: JobDao,
    private val appDataBase: AppDataBase
) : JobLocalDataSource {

    override suspend fun insertJobList(jobList: List<JobDto>) {
        appDataBase.withTransaction {
            jobDao.insertJobList(jobList)
        }
    }

    override suspend fun getLocationList(): Flow<List<String>> {
        return jobDao.getLocationList()
    }

    override suspend fun getRoleList(): Flow<List<String>> {
        return jobDao.getRoleList()
    }
}