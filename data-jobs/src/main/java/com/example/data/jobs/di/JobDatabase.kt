package com.example.data.jobs.di

import com.example.data.jobs.repositories.local.JobDao

interface JobDatabase {
    fun jobDao(): JobDao
}