package com.example.domain_user.usecase

import com.example.base.models.UserInfoEntity
import com.example.base.util.IoDispatcher
import com.example.base.util.SubjectUseCase
import com.example.domain_user.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserInfoList @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val userRepository: UserRepository
): SubjectUseCase<Unit, List<UserInfoEntity>>(dispatcher)  {
    override suspend fun createObservable(params: Unit): Flow<List<UserInfoEntity>> {
        return userRepository.getUserInfoList()
    }
}