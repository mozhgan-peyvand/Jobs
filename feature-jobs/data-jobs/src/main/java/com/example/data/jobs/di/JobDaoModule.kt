package com.example.data.jobs.di

import com.example.data.jobs.repositories.local.JobDatabase
import com.example.data.jobs.repositories.local.JobDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class JobDaoModule {

    companion object {
        @Provides
        fun bindJobDataBase(jobDatabase: JobDatabase): JobDao {
            return jobDatabase.jobDao()
        }
    }

}
