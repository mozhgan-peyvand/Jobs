package com.example.builder.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.jobs.repositories.local.JobDatabase
import com.example.data.user.repositories.local.UserDataBase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DatabaseModule {

    @Binds
    @Singleton
    abstract fun bindJobDataBase(appDatabase: AppDataBase): JobDatabase

    @Binds
    @Singleton
    abstract fun bindUserDataBase(appDatabase: AppDataBase): UserDataBase

    @Binds
    @Singleton
    abstract fun bindDataBase(appDatabase: AppDataBase): RoomDatabase

    companion object {
        @Singleton
        @Provides
        fun provideDb(@ApplicationContext context: Context): AppDataBase {
            return Room
                .databaseBuilder(context, AppDataBase::class.java, "com.mozhgan.peivandian.db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}