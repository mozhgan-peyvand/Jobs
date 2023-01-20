package com.example.domain_jobs.usecase

import com.example.domain_jobs.repository.GetJobRepository
import javax.inject.Inject

class GetAllJobRequest @Inject constructor(
    private val getJobRepository: GetJobRepository
) {
    suspend operator fun invoke() = getJobRepository.getAllJobs()

}
