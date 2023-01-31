package com.example.builder.db

import android.content.Context
import androidx.room.Room
import com.example.data.jobs.repositories.JobDao
import com.example.data.jobs.repositories.JobDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Job
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DatabaseModule {

    @Binds
    @Singleton
     abstract fun bindJobDataBase(appDatabase: AppDataBase): JobDatabase

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