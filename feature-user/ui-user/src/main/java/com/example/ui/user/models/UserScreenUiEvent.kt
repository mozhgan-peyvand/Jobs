package com.example.ui.user.models

sealed class UserScreenUiEvent {
    object GetUserInfoList: UserScreenUiEvent()
}