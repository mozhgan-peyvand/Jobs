package com.example.data.jobs.repositories

import com.example.domain_jobs.model.JobModel
import com.example.domain_jobs.repository.GetJobRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetJobRepositoryImp @Inject constructor(
    private val jobRemoteDataSource: JobRemoteDataSource,
    private val getJobLocalDataSource: JobLocalDataSource
) : GetJobRepository {
    override suspend fun getAllRoles(): Flow<List<String>> {
        return getJobLocalDataSource.getRoleList()
    }

    override suspend fun getAllLocation(): Flow<List<String>> {
        return getJobLocalDataSource.getLocationList()
    }

    override suspend fun getAllJobs(): List<JobModel>? {
        val result = jobRemoteDataSource.getAllJobList()
        result?.takeIf { it.isNotEmpty() }?.let {
            getJobLocalDataSource.insertJobList(it.map { jobResponse ->
                jobResponse.toJobDto()
            })
        }
        return result?.map { it.toGetJob() }
    }

    override suspend fun filterJobsList(role: String?, city: String?): List<JobModel>? {
        return jobRemoteDataSource.getFilterJobList(role = role, city = city)?.map { it.toGetJob() }
    }

}