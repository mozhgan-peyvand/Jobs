package com.example.data.user.repositories.local

import com.example.base.models.UserInfoEntity
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {
    suspend fun inserUserInfo(userInfoList: List<UserInfoEntity>):Unit
    suspend fun getUserInfoList(): Flow<List<UserInfoEntity>>
}