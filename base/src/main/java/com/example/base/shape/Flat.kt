package com.example.base.shape

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.drawscope.ContentDrawScope

class Flat(override val cornerShape: CornerShape) : NeuShape(cornerShape) {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun draw(drawScope: ContentDrawScope, style: NeuStyle) {
        drawScope.drawBackgroundShadows(this, style)
        drawScope.drawContent()
    }
}