package com.example.domain_jobs.usecase

import com.example.base.IoDispatcher
import com.example.domain_jobs.model.GetJob
import com.example.domain_jobs.repository.GetJobRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class FilterJobList @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
   private val repository: GetJobRepository
): ResultUseCase<FilterJobList.Params,List<GetJob>?>(dispatcher) {

    override suspend fun doWork(params: Params): List<GetJob>? {
        return repository.filterJobsList(params.role,params.city)
    }

    data class Params(val role: String?,val city: String?)
}