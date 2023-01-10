package com.example.base.shape

import androidx.compose.ui.unit.Dp

data class NeuStyle(
    val lightShadowColor: androidx.compose.ui.graphics.Color,
    val darkShadowColor: androidx.compose.ui.graphics.Color,
    val shadowElevation: Dp,
    val lightSource: LightSource
)