package com.example.base.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserInfoEntity(
    @PrimaryKey
    val title: Int = 0,
    val image: Int ,
    val link: Int? = null
)
