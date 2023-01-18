package com.example.base.util.shape

import androidx.compose.ui.unit.Dp


sealed class CornerShape {

}

object Oval : CornerShape()

class RoundedCorner(val radius: Dp) : CornerShape()
