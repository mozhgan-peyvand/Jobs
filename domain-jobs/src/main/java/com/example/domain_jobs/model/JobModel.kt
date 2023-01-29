package com.example.domain_jobs.model

data class JobModel(
    val id: String? = null,
    val role: String? = null,
    val company_name: String? = null,
    val company_num_employees: String? = null,
    val employment_type: String? = null,
    val location: String? = null,
    val remote: Boolean? = null,
    val logo: String? = null,
    val url: String? = null,
    val text: String? = null,
    val date_posted: String? = null,
    val keywords: List<String>? = null,
    val source: String? = null
)