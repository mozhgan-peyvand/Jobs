package com.example.domain_jobs.usecase

import com.example.base.util.IoDispatcher
import com.example.base.util.SubjectUseCase
import com.example.domain_jobs.repository.GetJobRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllLocation @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val getJobRepository: GetJobRepository
) : SubjectUseCase<Unit, List<String>>(dispatcher){
    override suspend fun createObservable(params: Unit): Flow<List<String>> {
        return getJobRepository.getAllLocation()
    }
}