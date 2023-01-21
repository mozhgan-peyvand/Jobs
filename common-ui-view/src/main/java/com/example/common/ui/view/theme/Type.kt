package com.example.common.ui.view.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.common.ui.view.R


//if we use of color here we should use of Color class than not relate on theme and MaterialTheme
val AppTypography = Typography(
    defaultFontFamily = FontFamily(
        Font(R.font.quicksand_light, FontWeight.W300),
        Font(R.font.quicksand_medium, FontWeight.W400),
        Font(R.font.quicksand_regular, FontWeight.W500),
        Font(R.font.quicksand_bold, FontWeight.W600),
    ),
    h1 = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 35.sp,
    ),
    h2 = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 24.sp,
    ),
    h3 = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 20.sp,
    ),
    h4 = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 18.sp,
    ),
    h5 = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
    ),
    h6 = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
    ),
    subtitle1 = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
    ),
    subtitle2 = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
    ),
    body1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 15.sp,
    ),
    caption = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    overline = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 12.sp
    )
)

//if we need to color
@Composable
fun Typography.h3Primary(): TextStyle {
    return h3.copy(
        color = MaterialTheme.colors.primary
    )
}

@Composable
fun Typography.captionOnBackground(): TextStyle {
    return caption.copy(
        color = MaterialTheme.colors.onBackground
    )
}

@Composable
fun Typography.overLineOnPrimary(): TextStyle {
    return overline.copy(
        color = MaterialTheme.colors.onPrimary
    )
}
