package com.hompiler.whiteboard.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

interface Drawable {
    var draw: DrawScope.() -> Unit
    var color: Color
}

abstract class Shape(
    private val startOffset: Offset,
    endOffset: Offset = startOffset,
    override var color: Color,
) : Drawable {
    var endOffsetState = mutableStateOf(endOffset)

}


class Rectangle(
    startOffset: Offset,
    endOffset: Offset = startOffset,
    color: Color = Color.Black,
) : Shape(startOffset, endOffset, color) {


    override var draw: DrawScope.() -> Unit = {
        drawRoundRect(
            color = color,
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
    color: Color = Color.Black
) : Shape(startOffset, endOffset, color) {

    override var draw: DrawScope.() -> Unit = {
        drawOval(
            color = color,
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


class Arrow(
    startOffset: Offset,
    endOffset: Offset = startOffset,
    color: Color = Color.Black
) : Shape(startOffset, endOffset, color) {

    override var draw: DrawScope.() -> Unit = {

        val path = Path().apply {
            moveTo(startOffset.x, startOffset.y)
            lineTo(endOffsetState.value.x, endOffsetState.value.y)
            close()

            var xDir = if (startOffset.y - endOffsetState.value.y > 0) endOffsetState.value.y + 50
            else endOffsetState.value.y - 50

            moveTo(endOffsetState.value.x, endOffsetState.value.y)
            lineTo(endOffsetState.value.x + 50, xDir)
            close()
            moveTo(endOffsetState.value.x, endOffsetState.value.y)
            lineTo(endOffsetState.value.x - 50, xDir)

            close()
        }

        drawPath(path, color = color, style = Stroke(4.dp.toPx()))

    }
}

class Pencil(
    var points: MutableState<MutableList<Offset>> = mutableStateOf(mutableListOf()),
    override var color: Color = Color.Black
): Drawable {

    fun addPoint() {
//        points.value
    }

    override var draw: DrawScope.() -> Unit = {
        drawPath(
            path = Path().apply {
                points.value.forEachIndexed { i, point ->
                    if (i == 0) {
                        moveTo(point.x, point.y)
                    } else {
                        lineTo(point.x, point.y)
                    }
                }
            },
            color = color,
            style = Stroke(4.dp.toPx())
        )
    }

}

