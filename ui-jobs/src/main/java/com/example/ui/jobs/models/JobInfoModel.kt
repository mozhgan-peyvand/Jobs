package com.example.ui.jobs.models

import com.example.domain_jobs.model.GetJob

data class JobInfoModel(
    val id: String? = null,
    val companyName: String? = null,
    val locationCompany: String? = null,
    val employmentType: String? = null,
    val role: String? = null,
    val description: String? = null,
    val imageRes : String? = null,
    )


fun com.example.domain_jobs.model.GetJob.toViewJob() = JobInfoModel(
    id = id,
    companyName = company_name,
    locationCompany = location,
    employmentType = employment_type,
    role = role,
    description = text,
    imageRes = logo
)