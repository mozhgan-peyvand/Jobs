package com.example.data.jobs.models

import com.example.base.models.JobDto
import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class JobResponse(
    val id: String? = null,
    val role: String? = null,
    @Json(name = "company_name")
    val companyName: String? = null,
    @Json(name = "company_num_employees")
    val companyNumberEmployees: String? = null,
    @Json(name = "employment_type")
    val employmentType: String? = null,
    val location: String? = null,
    val remote: Boolean? = null,
    val logo: String? = null,
    val url: String? = null,
    val text: String? = null,
    @Json(name = "date_posted")
    val datePosted: String? = null,
    val keywords: List<String>? = null,
    val source: String? = null
) {

    fun toJobDto() = JobDto(
        id = id ?: "",
        role = role,
        companyName = companyName,
        companyNumEmployees = companyNumberEmployees,
        employmentType = employmentType,
        location = location,
        remote = remote,
        logo = logo,
        url = url,
        jobDetail= text,
        datePosted = datePosted
    )
}
