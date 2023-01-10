package com.example.base.shape2

import androidx.compose.ui.unit.Dp


sealed class CornerShape {

}

object Oval : CornerShape()

class RoundedCorner(val radius: Dp) : CornerShape()
