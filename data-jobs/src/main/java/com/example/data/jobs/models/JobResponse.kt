package com.example.data.jobs.models

import com.example.base.JobDto
import com.example.domain_jobs.model.JobModel
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
){
    fun toGetJob() = JobModel(
        id = id,
        role = role,
        company_name = company_name,
        company_num_employees = company_num_employees,
        employment_type = employment_type,
        location = location,
        remote = remote,
        logo = logo,
        url = url,
        text = text,
        date_posted = date_posted,
        keywords = keywords,
        source = source
    )

    fun toJobDto() = JobDto(
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
