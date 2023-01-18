package com.example.base.util.shape

import androidx.compose.ui.graphics.drawscope.ContentDrawScope

abstract class NeuShape(open val cornerShape: CornerShape) {
    abstract fun draw(drawScope: ContentDrawScope, style: NeuStyle)
}