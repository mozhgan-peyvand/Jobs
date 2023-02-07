package com.example.data_android.di

import com.example.data.user.repositories.local.UserLocalDataSource
import com.example.data_android.dataSourceImp.userDataSourceImp.UserLocalDataSouceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UserDataSourceModule {

    @Binds
    fun provideUserLocalDataSource( userLocalDataSouceImp: UserLocalDataSouceImp): UserLocalDataSource
}