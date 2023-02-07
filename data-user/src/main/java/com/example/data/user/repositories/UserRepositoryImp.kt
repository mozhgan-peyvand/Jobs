package com.example.data.user.repositories

import com.example.base.models.UserInfoEntity
import com.example.data.user.repositories.local.UserLocalDataSource
import com.example.domain_user.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource
): UserRepository {

    override suspend fun getUserInfoList(): Flow<List<UserInfoEntity>> {
        return userLocalDataSource.getUserInfoList()
    }

    override suspend fun insertUserInfo(userInfoList: List<UserInfoEntity>) {
        userLocalDataSource.inserUserInfo(userInfoList = userInfoList)
    }
}