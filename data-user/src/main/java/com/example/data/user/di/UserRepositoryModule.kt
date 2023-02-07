package com.example.data.user.di

import com.example.data.user.repositories.UserRepositoryImp
import com.example.domain_user.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UserRepositoryModule {
    @Binds
    fun provideUserRepository(userRepositoryImp: UserRepositoryImp): UserRepository
}