package com.example.domain_user.repository

import com.example.base.models.UserInfoEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun insertUserInfo( userInfoList: List<UserInfoEntity>): Unit
    suspend fun getUserInfoList(): Flow<List<UserInfoEntity>>
}