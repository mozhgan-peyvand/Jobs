package com.example.data.jobs.repositories

import androidx.room.*
import com.example.data.jobs.models.JobDto
import kotlinx.coroutines.flow.Flow

@Dao
interface JobDao {

    @Transaction
    @Query("SELECT * FROM JobDto")
    fun getJobList(): Flow<List<JobDto>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJobList(jobList: List<JobDto>)

    @Transaction
    @Query("SELECT DISTINCT location FROM JobDto WHERE location IS NOT NULL ORDER BY location")
    fun getLocationList(): Flow<List<String>>

    @Transaction
    @Query("SELECT DISTINCT role FROM JobDto ORDER BY role")
    fun getRoleList(): Flow<List<String>>
}