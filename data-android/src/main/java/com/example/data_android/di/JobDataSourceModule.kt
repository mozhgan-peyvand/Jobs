package com.example.data_android.di

import com.example.data.jobs.repositories.JobLocalDataSource
import com.example.data.jobs.repositories.JobRemoteDataSource
import com.example.data_android.dataSourceImp.JobLocalDataSourceImp
import com.example.data_android.dataSourceImp.JobRemoteDataSourceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface JobDataSourceModule  {

    @Binds
    fun provideJobRemote(jobRemoteDataSourceImp: JobRemoteDataSourceImp) : JobRemoteDataSource

    @Binds
    fun provideJobLocal(jobLocalDataSourceImp: JobLocalDataSourceImp) : JobLocalDataSource

}