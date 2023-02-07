package com.example.data_android.dataSourceImp.userDataSourceImp

import androidx.room.RoomDatabase
import androidx.room.withTransaction
import com.example.base.models.UserInfoEntity
import com.example.data.user.repositories.local.UserDao
import com.example.data.user.repositories.local.UserLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserLocalDataSouceImp @Inject constructor(
    private val userDao: UserDao
): UserLocalDataSource {

    @Inject
    lateinit var roomDatabase: RoomDatabase

    override suspend fun getUserInfoList(): Flow<List<UserInfoEntity>> {
        return userDao.getUserInfo()
    }

    override suspend fun inserUserInfo(userInfoList: List<UserInfoEntity>) {
        roomDatabase.withTransaction {
            userDao.deleteTable()
            userDao.insertUserInfo(userInfoList)
        }
    }

}