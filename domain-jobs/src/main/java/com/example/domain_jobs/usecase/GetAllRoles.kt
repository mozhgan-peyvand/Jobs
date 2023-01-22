package com.example.domain_jobs.usecase

import com.example.domain_jobs.repository.GetJobRepository
import javax.inject.Inject

class GetAllRoles @Inject constructor(
    private val repository: GetJobRepository
) {
    operator fun invoke() = repository.getAllRoles()
}