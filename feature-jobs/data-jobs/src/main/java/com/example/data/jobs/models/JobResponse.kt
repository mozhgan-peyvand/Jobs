package com.example.data.jobs.models

import com.example.base.models.JobDto
import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class JobResponse(
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
) {

    fun toJobDto() = JobDto(
        id = id ?: "",
        role = role,
        companyName = company_name,
        companyNumEmployees = company_num_employees,
        employmentType = employment_type,
        location = location,
        remote = remote,
        logo = logo,
        url = url,
        datePosted = date_posted
    )
}
