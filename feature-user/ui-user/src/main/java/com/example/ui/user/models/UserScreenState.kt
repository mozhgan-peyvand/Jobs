package com.example.ui.user.models

import com.example.base.models.UserInfoEntity
import com.example.base.util.AsyncResult
import com.example.base.util.Uninitialized
import javax.annotation.concurrent.Immutable

@Immutable
data class UserScreenState(
    val userInfoList: AsyncResult<List<UserInfoEntity>> = Uninitialized,
    val insertUserInfo: AsyncResult<Unit> = Uninitialized
)