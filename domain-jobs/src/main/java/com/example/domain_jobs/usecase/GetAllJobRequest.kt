package com.example.domain_jobs.usecase

import com.example.base.util.IoDispatcher
import com.example.domain_jobs.model.JobModel
import com.example.domain_jobs.repository.GetJobRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetAllJobRequest @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val getJobRepository: GetJobRepository
): ResultUseCase<Unit,List<JobModel>?>(dispatcher) {
    override suspend fun doWork(params: Unit): List<JobModel>? {
        return getJobRepository.getAllJobs()
    }
}
