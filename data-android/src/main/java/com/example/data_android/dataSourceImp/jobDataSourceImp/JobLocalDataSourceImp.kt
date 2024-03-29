package com.example.data_android.dataSourceImp.jobDataSourceImp

import androidx.room.RoomDatabase
import androidx.room.withTransaction
import com.example.base.models.JobDto
import com.example.data.jobs.repositories.local.JobDao
import com.example.data.jobs.repositories.local.JobLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class JobLocalDataSourceImp @Inject constructor(
    private val jobDao: JobDao
) : JobLocalDataSource {

    @Inject
    lateinit var roomDatabase: RoomDatabase

    override suspend fun insertJobList(jobList: List<JobDto>, page: Int) {
        roomDatabase.withTransaction {
            if (page == 1) {
                jobDao.deleteTable()
            }
            jobDao.insertJobList(jobList)
        }
    }

    override suspend fun getAllJobList(): Flow<List<JobDto>?> {
        return jobDao.getJobList()
    }

    override suspend fun getJobDetailInfo(jobId: String): Flow<JobDto> {
        return jobDao.getJobDetailInfo(jobId)
    }
}