package com.example.data.jobs.repositories.local

import androidx.room.*
import com.example.base.models.JobDto
import kotlinx.coroutines.flow.Flow

@Dao
interface JobDao {

    @Transaction
    @Query("SELECT * FROM JobDto")
    fun getJobList(): Flow<List<JobDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJobList(jobList: List<JobDto>)

    @Transaction
    @Query("SELECT DISTINCT location FROM JobDto WHERE location IS NOT NULL ORDER BY location")
    fun getLocationList(): Flow<List<String>>

    @Transaction
    @Query("SELECT DISTINCT role FROM JobDto ORDER BY role")
    fun getRoleList(): Flow<List<String>>

    @Transaction
    @Query("SELECT * FROM JobDto WHERE role=:role")
    fun getFilterJobListWithRole(role: String): Flow<List<JobDto>>

    @Transaction
    @Query("SELECT * FROM JobDto WHERE location=:city ")
    fun getFilterJobListWithCity(city: String): Flow<List<JobDto>>

    @Transaction
    @Query("SELECT * FROM JobDto WHERE role=:role AND location=:city ")
    fun getFilterJobList(role: String,city: String): Flow<List<JobDto>>

    @Transaction
    @Query("DELETE FROM JobDto")
    fun deleteTable()

    @Transaction
    @Query("SELECT * FROM JobDto WHERE id=:id")
    fun getJobDetailInfo(id: String): Flow<JobDto>
}