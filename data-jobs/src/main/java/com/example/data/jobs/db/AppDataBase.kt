package com.example.data.jobs.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.jobs.models.GetJobDTO
import com.example.data.jobs.repositories.JobDao


@Database(
    entities = [GetJobDTO::class],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun jobDao(): JobDao
}