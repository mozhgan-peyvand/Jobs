package com.example.domain_jobs.usecase

import com.example.base.models.JobDto
import com.example.base.util.IoDispatcher
import com.example.base.util.SubjectUseCase
import com.example.domain_jobs.repository.GetJobRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetJobDetailInfo @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val getJobRepository: GetJobRepository
):SubjectUseCase<GetJobDetailInfo.Param,JobDto>(dispatcher) {

    data class Param(
        val jobId: String
    )

    override suspend fun createObservable(params: Param): Flow<JobDto> {
        return getJobRepository.getJobDetailInfo(params.jobId)
    }

}