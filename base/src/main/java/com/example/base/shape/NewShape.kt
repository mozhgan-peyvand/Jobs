package com.example.base.shape

import androidx.compose.ui.graphics.drawscope.ContentDrawScope

interface NeuShape {

    fun drawShadows(drawScope: ContentDrawScope, blurMaker: BlurMaker, shapeConfig: ShapeConfig)
}