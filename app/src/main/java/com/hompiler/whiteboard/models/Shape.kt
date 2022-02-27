package com.hompiler.whiteboard.models

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin


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
            cornerRadius = CornerRadius(10f, 10f),
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

        var deltaX = endOffsetState.value.x - startOffset.x
        var deltaY = endOffsetState.value.y - startOffset.y

        var angle = atan2(deltaY, deltaX)


        val path = Path().apply {
            moveTo(startOffset.x, startOffset.y)
            lineTo(endOffsetState.value.x, endOffsetState.value.y)
            close()

            val headLength = 40f


            moveTo(endOffsetState.value.x, endOffsetState.value.y)
            lineTo(
                (endOffsetState.value.x - headLength * cos(angle + Math.PI / 6)).toFloat(),
                (endOffsetState.value.y - headLength * sin(angle + Math.PI / 6)).toFloat()
            )

            close()

            moveTo(endOffsetState.value.x, endOffsetState.value.y)
            lineTo(
                (endOffsetState.value.x - headLength * cos(angle - Math.PI / 6)).toFloat(),
                (endOffsetState.value.y - headLength * sin(angle - Math.PI / 6)).toFloat()
            )

            close()
        }

        drawPath(path, color = color, style = Stroke(4.dp.toPx()))

    }
}

