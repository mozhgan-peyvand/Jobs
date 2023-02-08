package com.example.data.user.repositories.local

import androidx.room.*
import com.example.base.models.UserInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserInfo(userInfoList: List<UserInfoEntity>)

    @Transaction
    @Query("SELECT * FROM UserInfoEntity")
    fun getUserInfo(): Flow<List<UserInfoEntity>>

    @Transaction
    @Query("DELETE FROM UserInfoEntity")
    fun deleteTable()

}