package com.example.domain_jobs.usecase

import com.example.domain_jobs.repository.GetJobRepository
import javax.inject.Inject

class GetAllLocation @Inject constructor(
    private val getJobRepository: GetJobRepository
){
    operator fun invoke() = getJobRepository.getAllLocation()
}