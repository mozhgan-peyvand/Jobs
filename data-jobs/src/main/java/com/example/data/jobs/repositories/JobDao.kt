package com.example.data.jobs.repositories

import androidx.room.*
import com.example.data.jobs.models.GetJobDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface JobDao {

    @Transaction
    @Query("SELECT * FROM GetJobDTO")
    fun getJobList(): Flow<List<GetJobDTO>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJobList(jobList: List<GetJobDTO>)

    @Transaction
    @Query("SELECT DISTINCT location FROM GetJobDTO ORDER BY location")
    fun getLocationList(): Flow<List<String>>

    @Transaction
    @Query("SELECT DISTINCT role FROM GetJobDTO ORDER BY role")
    fun getRoleList(): Flow<List<String>>
}