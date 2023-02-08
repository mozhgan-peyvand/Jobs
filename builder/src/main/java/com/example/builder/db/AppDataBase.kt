package com.example.builder.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.base.models.JobDto
import com.example.base.models.UserInfoEntity
import com.example.data.jobs.repositories.local.JobDatabase
import com.example.data.user.repositories.local.UserDataBase


@Database(
    entities = [JobDto::class, UserInfoEntity::class],
    version = 8
)
abstract class AppDataBase : RoomDatabase(), JobDatabase, UserDataBase