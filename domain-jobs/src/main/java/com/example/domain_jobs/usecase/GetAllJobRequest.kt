package com.example.domain_jobs.usecase

import com.example.base.IoDispatcher
import com.example.base.api.Resource
import com.example.domain_jobs.model.GetJob
import com.example.domain_jobs.repository.GetJobRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetAllJobRequest @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val getJobRepository: GetJobRepository
): ResultUseCase<Unit,List<GetJob>?>(dispatcher) {
    override suspend fun doWork(params: Unit): List<GetJob>? {
        return getJobRepository.getAllJobs()
    }
}
