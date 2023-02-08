package com.example.data.jobs.repositories.local

import com.example.data.jobs.repositories.local.JobDao

interface JobDatabase {
    fun jobDao(): JobDao
}