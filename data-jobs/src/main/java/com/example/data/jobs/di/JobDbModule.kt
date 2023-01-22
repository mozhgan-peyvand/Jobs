package com.example.data.jobs.di

import com.example.data.jobs.db.AppDataBase
import com.example.data.jobs.repositories.JobDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class JobDbModule {

    @Provides
    @Singleton
    fun provideJobDao(appDataBase: AppDataBase): JobDao {
        return appDataBase.jobDao()
    }
}
