package com.example.data_android.dataSourceImp

import androidx.room.RoomDatabase
import androidx.room.withTransaction
import com.example.base.JobDto
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

    override suspend fun getLocationList(): Flow<List<String>> {
        return jobDao.getLocationList()
    }

    override suspend fun getRoleList(): Flow<List<String>> {
        return jobDao.getRoleList()
    }

    override suspend fun filterJobList(role: String, city: String): Flow<List<JobDto>> {
        if (role.isEmpty() && city.isNotEmpty()) {
            return jobDao.getFilterJobListWithCity(city)
        }
        if (role.isNotEmpty() && city.isEmpty()) {
            return jobDao.getFilterJobListWithRole(role)
        }
        if (role.isEmpty() && city.isEmpty()) {
            return jobDao.getJobList()
        }

        return jobDao.getFilterJobList(role = role, city = city)

    }
}