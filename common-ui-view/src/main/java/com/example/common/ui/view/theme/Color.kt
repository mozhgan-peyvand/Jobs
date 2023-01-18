package com.example.common.ui.view.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object AppColors {
    val Purple200 = Color(0xFF40B4B8)
    val Purple500 = Color(0xFF256967)

    object Light {
        val Background = Color(0xFFDCDCDC)
        val LightShadow = Color(0xFFFFFFFF)
        val DarkShadow = Color(0xFFA8B5C7)
        val TextColor = Color.Black
    }

    object Dark {
        val Background = Color(0xFF303234)
        val LightShadow = Color(0x66494949)
        val DarkShadow = Color(0x66000000)
        val TextColor = Color.White
    }

    @Composable
    fun lightShadow() = if (MaterialTheme.colors.isLight) Light.LightShadow else Dark.LightShadow

    @Composable
    fun darkShadow() = if (MaterialTheme.colors.isLight) Light.DarkShadow else Dark.DarkShadow
}