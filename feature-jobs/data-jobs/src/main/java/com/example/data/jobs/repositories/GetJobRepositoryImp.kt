package com.example.data.jobs.repositories

import com.example.base.models.JobDto
import com.example.data.jobs.repositories.local.JobLocalDataSource
import com.example.data.jobs.repositories.remote.JobRemoteDataSource
import com.example.domain_jobs.repository.GetJobRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetJobRepositoryImp @Inject constructor(
    private val jobRemoteDataSource: JobRemoteDataSource,
    private val jobLocalDataSource: JobLocalDataSource
) : GetJobRepository {

    override suspend fun insertJobList(page: Int) {
        val result = jobRemoteDataSource.getAllJobList(page = page)
        result?.takeIf { it.isNotEmpty() }?.let {
            jobLocalDataSource.insertJobList(it.map { jobResponse ->
                jobResponse.toJobDto()
            }, page = page)
        }
    }

    override suspend fun getJobList(): Flow<List<JobDto>?> {
        return jobLocalDataSource.getAllJobList()
    }

    override suspend fun getJobDetailInfo(jobId: String): Flow<JobDto> {
        return jobLocalDataSource.getJobDetailInfo(jobId = jobId)
    }

}