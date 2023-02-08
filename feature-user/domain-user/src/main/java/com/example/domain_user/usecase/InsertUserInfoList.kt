package com.example.domain_user.usecase

import com.example.base.models.UserInfoEntity
import com.example.base.util.IoDispatcher
import com.example.base.util.NoResultUseCase
import com.example.domain_user.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class InsertUserInfoList @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val userRepository: UserRepository
) : NoResultUseCase<InsertUserInfoList.Param>(dispatcher) {
    override suspend fun doWork(params: Param) {
        userRepository.insertUserInfo(params.userInfoList)
    }

    data class Param(
        val userInfoList: List<UserInfoEntity>
    )
}