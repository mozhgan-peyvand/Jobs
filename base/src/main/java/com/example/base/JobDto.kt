package com.example.base

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class JobDto(
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
)
