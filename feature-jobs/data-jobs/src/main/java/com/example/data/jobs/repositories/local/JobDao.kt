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
    @Query("DELETE FROM JobDto")
    fun deleteTable()

    @Transaction
    @Query("SELECT * FROM JobDto WHERE id=:id")
    fun getJobDetailInfo(id: String): Flow<JobDto>
}