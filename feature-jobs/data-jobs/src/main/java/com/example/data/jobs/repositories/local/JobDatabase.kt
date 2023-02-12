package com.example.data.jobs.repositories.local


interface JobDatabase {
    fun jobDao(): JobDao
}