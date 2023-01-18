package com.example.common.ui.view.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkThemeColors = darkColors(
    primary = AppColors.Purple200,
    onPrimary = Color.White,

    secondary = AppColors.Purple500,

    background = AppColors.Dark.Background,
    onBackground = Color.White,
    surface = AppColors.Dark.Background,
    onSurface = Color.White,
)

private val LightThemeColors = lightColors(
    primary = AppColors.Purple200,
    onPrimary = Color.Black,

    secondary = AppColors.Purple500,

    background = AppColors.Light.Background,
    onBackground = Color.Black,
    surface = AppColors.Light.Background,
    onSurface = Color.Black,
)
//private val otherThemeColors = Colors(
//
//)

@Composable
fun AppTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {
//    CompositionLocalProvider(
//        LocalRippleTheme provides SecondaryRippleTheme
//    ) {
        MaterialTheme(
            colors = if (darkTheme) DarkThemeColors else LightThemeColors,
            content = content,
            shapes = AppShapes,
            typography = AppTypography
        )
//    }
}

//@Immutable
//object SecondaryRippleTheme : RippleTheme {
//    @Composable
//    override fun defaultColor() = RippleTheme.defaultRippleColor(
//        contentColor = MaterialTheme.colors.background,
//        lightTheme = MaterialTheme.colors.isLight
//    )
//
//    @Composable
//    override fun rippleAlpha() = RippleTheme.defaultRippleAlpha(
//        contentColor = MaterialTheme.colors.background,
//        lightTheme = MaterialTheme.colors.isLight
//    )
//}