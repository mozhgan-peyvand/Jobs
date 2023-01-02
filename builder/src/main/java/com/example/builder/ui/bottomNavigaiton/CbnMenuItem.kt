package com.example.builder.ui.bottomNavigaiton
import androidx.annotation.*


data class CbnMenuItem(
    @DrawableRes
    val icon: Int,
    @DrawableRes
    val avdIcon: Int,
    val destinationId: String
)