package com.example.data.jobs.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import com.example.data.jobs.db.AppDataBase
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalJobModule {

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): AppDataBase {
        return Room
            .databaseBuilder(context, AppDataBase::class.java, "com.mozhgan.peivandian.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}