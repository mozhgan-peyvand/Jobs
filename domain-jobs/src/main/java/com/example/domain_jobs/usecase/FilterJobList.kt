package com.example.domain_jobs.usecase

import com.example.base.models.JobDto
import com.example.base.util.IoDispatcher
import com.example.domain_jobs.repository.GetJobRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FilterJobList @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val repository: GetJobRepository
): SubjectUseCase<FilterJobList.Params,List<JobDto>?>(dispatcher) {

    override suspend fun createObservable(params: Params): Flow<List<JobDto>?> {
        return repository.filterJobsList(params.role,params.city)
    }

    data class Params(val role: String?,val city: String?)


}