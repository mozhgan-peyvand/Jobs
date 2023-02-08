package com.example.data.jobs.di

import com.example.data.jobs.repositories.GetJobRepositoryImp
import com.example.domain_jobs.repository.GetJobRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface JobRepositoryModule {

    @Binds
    @Singleton
    fun provideGetJobRepository(getJobRepositoryImp: GetJobRepositoryImp): com.example.domain_jobs.repository.GetJobRepository
}