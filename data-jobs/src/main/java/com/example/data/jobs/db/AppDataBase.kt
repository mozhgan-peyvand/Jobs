package com.example.data.jobs.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.jobs.models.JobDto
import com.example.data.jobs.repositories.JobDao


@Database(
    entities = [JobDto::class],
    version = 2
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun jobDao(): JobDao
}