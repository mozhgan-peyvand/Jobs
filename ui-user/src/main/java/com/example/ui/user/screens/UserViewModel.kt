package com.example.ui.user.screens

import androidx.lifecycle.DefaultLifecycleObserver
import com.example.base.models.UserInfoEntity
import com.example.base.util.BaseViewModel
import com.example.domain_user.usecase.GetUserInfoList
import com.example.domain_user.usecase.InsertUserInfoList
import com.example.ui.user.R
import com.example.ui.user.models.UserScreenState
import com.example.ui.user.models.UserScreenUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserInfoList: GetUserInfoList,
    private val insertUserInfoList: InsertUserInfoList
) : BaseViewModel<UserScreenState, UserScreenUiEvent>(UserScreenState()), DefaultLifecycleObserver {

    init {
        insertUserInfo()

        onAsyncResult(
            UserScreenState::insertUserInfo,
            onSuccess = {
                getUserInfoList()
            }
        )
    }

    private fun getUserInfoList() {
        getUserInfoList(Unit)
        getUserInfoList.flow.execute(
            reducer = { copy(userInfoList = it) }
        )
    }

    private fun insertUserInfo() {
        suspend {
            insertUserInfoList(
                InsertUserInfoList.Param(
                    listOf(
                        UserInfoEntity(
                            image = R.drawable.ic_github,
                            title = R.string.github_title,
                            link = R.string.link_github
                        ),
                        UserInfoEntity(
                            image = R.drawable.ic_linkedin,
                            title = R.string.linkedin_title,
                            link = R.string.link_linkedin
                        ),
                        UserInfoEntity(
                            image = R.drawable.ic_instagram,
                            title = R.string.instagram_title,
                            link = R.string.link_instagram
                        ),
                    )
                )
            )
        }.execute(
            reducer = { copy(insertUserInfo = it) }
        )
    }
}