package com.hompiler.whiteboard.models

import android.graphics.drawable.Drawable
import androidx.compose.ui.geometry.Offset

data class DrawingTool(
    val iconResource: Int,
    val contentDescription: String,
    var use: (startOffset: Offset) -> Drawing,
)
