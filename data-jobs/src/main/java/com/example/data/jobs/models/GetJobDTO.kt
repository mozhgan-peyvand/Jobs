package com.example.data.jobs.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain_jobs.model.GetJob

@Entity
data class GetJobDTO(
    @PrimaryKey(autoGenerate = true) val idJob: Int = 0,
    val role: String? = null,
    val companyName: String? = null,
    val companyNumEmployees: String? = null,
    val employmentType: String? = null,
    val location: String? = null,
    val remote: Boolean? = null,
    val logo: String? = null,
    val url: String? = null,
    val datePosted: String? = null,
){
    fun toGetJob() = GetJob(
        id = idJob.toString(),
        role = role,
        company_name = companyName,
        company_num_employees = companyNumEmployees,
        employment_type = employmentType,
        location = location,
        remote = remote,
        logo = logo,
        url = url,
        text = "",
        date_posted = "",
        keywords = listOf(),
        source = ""
    )
}
