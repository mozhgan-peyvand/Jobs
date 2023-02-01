package com.example.data.jobs.repositories

import com.example.base.JobDto
import com.example.domain_jobs.model.JobModel
import com.example.domain_jobs.repository.GetJobRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetJobRepositoryImp @Inject constructor(
    private val jobRemoteDataSource: JobRemoteDataSource,
    private val JobLocalDataSource: JobLocalDataSource
) : GetJobRepository {

    override suspend fun insertAllJobs() {
        val result = jobRemoteDataSource.getAllJobList()
        result?.takeIf { it.isNotEmpty() }?.let {
            JobLocalDataSource.insertJobList(it.map { jobResponse ->
                jobResponse.toJobDto()
            })
        }
    }

    override suspend fun getAllRoles(): Flow<List<String>> {
        return JobLocalDataSource.getRoleList()
    }

    override suspend fun getAllLocation(): Flow<List<String>> {
        return JobLocalDataSource.getLocationList()
    }

    override suspend fun getAllJobs(): Flow<List<JobDto>?> {
        return JobLocalDataSource.getAllJobList()
    }

    override suspend fun filterJobsList(role: String?, city: String?): Flow<List<JobDto>> {
        return JobLocalDataSource.filterJobList(role ?: "",city?: "")
    }

}