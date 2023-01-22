package com.example.domain_jobs.usecase

import com.example.domain_jobs.repository.GetJobRepository
import javax.inject.Inject

class FilterJobList @Inject constructor(
   private val repository: GetJobRepository
) {
    suspend operator fun invoke (role: String?,city : String?) = repository.filterJobsList(role = role, city = city)
}