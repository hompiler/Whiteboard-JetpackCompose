package com.hompiler.whiteboard.models

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import java.nio.file.Path


enum class DrawingType {
    PENCIL, ARROW, RECTANGLE, CIRCLE
}

interface Drawable {
    var draw: DrawScope.() -> Unit
}

abstract class Drawing(
    val startOffset: Offset,
    endOffset: Offset = startOffset,
    var size: Size = Size(1f, 1f),
    var path: Path? = null,
) : Drawable {
    var endOffsetState = mutableStateOf(endOffset)

}


class Rectangle(
    startOffset: Offset,
    endOffset: Offset = startOffset,
) : Drawing(startOffset, endOffset) {


    override var draw: DrawScope.() -> Unit = {
        drawRoundRect(
            color = Color.Black,
            topLeft = startOffset,
            size = Size(
                endOffsetState.value.x - startOffset.x,
                endOffsetState.value.y - startOffset.y,
            ),
            style = Stroke(
                width = 4.dp.toPx()
            ),
            cornerRadius = CornerRadius(10f, 10f)
        )
    }
}

class Ellipse(
    startOffset: Offset,
    endOffset: Offset = startOffset,
    size: Size = Size(1f, 1f)
) : Drawing(startOffset, endOffset) {

    override var draw: DrawScope.() -> Unit = {
        drawOval(
            color = Color.Black,
            topLeft = startOffset,
            size = Size(
                endOffsetState.value.x - startOffset.x,
                endOffsetState.value.y - startOffset.y,
            ),
            style = Stroke(
                width = 4.dp.toPx()
            ),
        )
    }
}

class Pencil(
    startOffset: Offset,
    endOffset: Offset = startOffset,
    var points: List<Offset> = mutableListOf(), override var draw: DrawScope.() -> Unit = {}
) : Drawing(startOffset, endOffset)

class Arrow(
    startOffset: Offset,
    endOffset: Offset = startOffset, override var draw: DrawScope.() -> Unit = {},
) : Drawing(startOffset, endOffset) {

}


//class Rectangle(
//    startOffset: Offset,
//    endOffset: Offset,
//    size: S
//) : Drawing(startOffset, endOffset)