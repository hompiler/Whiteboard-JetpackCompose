package com.hompiler.whiteboard.models

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size

data class Drawing(
    val startOffset: Offset,
    var size: Size = Size(1f,1f),
)





//class Rectangle(
//    startOffset: Offset,
//    endOffset: Offset,
//    size: S
//) : Drawing(startOffset, endOffset)