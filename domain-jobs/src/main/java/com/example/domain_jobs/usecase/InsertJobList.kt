package com.example.domain_jobs.usecase

import com.example.base.util.IoDispatcher
import com.example.domain_jobs.repository.GetJobRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class InsertJobList @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val repository: GetJobRepository
) : NoResultUseCase<Unit>(dispatcher) {
    override suspend fun doWork(params: Unit) {
        return repository.insertAllJobs()
    }
}