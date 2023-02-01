package com.example.data.jobs.di

import com.example.data.jobs.repositories.JobDao
import com.example.data.jobs.di.JobDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

interface JobDatabase {
    fun jobDao(): JobDao
}