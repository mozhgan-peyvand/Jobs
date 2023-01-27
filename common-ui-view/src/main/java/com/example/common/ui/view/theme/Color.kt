package com.example.common.ui.view.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object AppColors {

    object Light {
        val primary = Color(0xFF00a794)
        val primaryVariant = Color(0xFF03dac4)
        val secondary = Color(0xFF6300ee)
        val onSecondary = Color(0xFFffc4ff)
        val Background = Color(0xFFDCDCDC)
        val LightShadow = Color(0xFFFFFFFF)
        val DarkShadow = Color(0xFFA8B5C7)
    }

    object Dark {
        val primary = Color(0xFF66fff7)
        val primaryVariant = Color(0xFF03dac4)
        val secondary = Color(0xFFffc4ff)
        val onSecondary = Color(0xFF7C2FE9)
        val Background = Color(0xFF303234)
        val LightShadow = Color(0x66494949)
        val DarkShadow = Color(0x66000000)
    }

    @Composable
    fun lightShadow() = if (MaterialTheme.colors.isLight) Light.LightShadow else Dark.LightShadow

    @Composable
    fun darkShadow() = if (MaterialTheme.colors.isLight) Light.DarkShadow else Dark.DarkShadow
}