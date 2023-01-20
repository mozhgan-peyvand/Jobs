package com.example.data.jobs.di

import com.example.data.jobs.repositories.JobService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ServiceJobModule {

    @Provides
    fun provideGetJob(retrofit: Retrofit): JobService {
        return retrofit.create(JobService::class.java)
    }
}