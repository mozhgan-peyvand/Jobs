package com.example.builder.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.base.JobDto
import com.example.data.jobs.repositories.JobDao
import com.example.data.jobs.di.JobDatabase


@Database(
    entities = [JobDto::class],
    version = 2
)
abstract class AppDataBase : RoomDatabase(), JobDatabase