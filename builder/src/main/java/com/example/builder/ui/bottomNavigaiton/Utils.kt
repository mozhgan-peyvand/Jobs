package com.example.builder.ui.bottomNavigaiton

import android.content.Context
import android.util.DisplayMetrics
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

/**
 * Created by suson on 10/1/20
 */

fun Int.toPx(context: Context) = (this * context.resources.displayMetrics.densityDpi) / DisplayMetrics.DENSITY_DEFAULT
