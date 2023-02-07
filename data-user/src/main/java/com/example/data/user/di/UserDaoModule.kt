package com.example.data.user.di

import com.example.data.user.repositories.local.UserDao
import com.example.data.user.repositories.local.UserDataBase
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserDaoModule {

    companion object{
        @Provides
        fun provideUserDao(userDataBase: UserDataBase): UserDao{
            return userDataBase.userDao()
        }
    }
}