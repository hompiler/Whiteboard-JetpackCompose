package com.hompiler.whiteboard.models

import android.graphics.drawable.Drawable

data class DrawingTool(
    val iconResource: Int,
    val contentDescription: String,
    var onClick: () -> Unit = {}
)
