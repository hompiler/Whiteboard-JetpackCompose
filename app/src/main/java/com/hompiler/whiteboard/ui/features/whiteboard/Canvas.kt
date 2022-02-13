package com.hompiler.whiteboard.ui.features.whiteboard

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.hompiler.whiteboard.models.Drawing
import com.hompiler.whiteboard.models.DrawingTool
import com.hompiler.whiteboard.models.Ellipse
import com.hompiler.whiteboard.models.Rectangle


@Composable
fun WhiteboardCanvas(
    selectedTool: DrawingTool,
    modifier: Modifier = Modifier
) {

    var startOffset by remember { mutableStateOf<Offset>(Offset(-1.0f, -1.0f)) }
    var endOffset by remember { mutableStateOf<Offset>(Offset(-1.0f, -1.0f)) }

    var points by remember {
        mutableStateOf(
            mutableListOf<Offset>()
        )
    }

    var drawings by remember {
        mutableStateOf(
            mutableListOf<Drawing>()
        )
    }


    val pointerModifier = modifier
        .pointerInput(selectedTool) {
            forEachGesture {

                awaitPointerEventScope {

                    // ACTION_DOWN
                    val down = awaitFirstDown()
                    startOffset = down.position



                    drawings.add(selectedTool.use(startOffset))
                    points.add(down.position)


                    do {

                        // ACTION_MOVE
                        val event: PointerEvent = awaitPointerEvent()
                        endOffset = event.changes[0].position
                        Log.wtf("MOVE", "$endOffset")
                        points.add(endOffset)

//                        drawings[drawings.lastIndex].size = Size(
//                            endOffset.x - startOffset.x,
//                            endOffset.y - startOffset.y,
//                        )

                        drawings[drawings.lastIndex].endOffsetState.value = endOffset

                        event.changes.forEach { pointerInputChange: PointerInputChange ->
                            pointerInputChange.consumePositionChange()


                        }
                    } while (event.changes.any { it.pressed })

                    // ACTION_UP is hee
                    Log.wtf("UP", "${drawings.last()}")
                }
            }
        }

    Canvas(
        modifier = pointerModifier
            .background(Color.Gray)
            .fillMaxSize()

    ) {
//
//        drawPath(
//            path = Path().apply {
//                points.forEachIndexed { i, point ->
//                    if (i == 0) {
//                        moveTo(point.x, point.y)
//                    } else {
//                        lineTo(point.x, point.y)
//                    }
//                }
//            },
//            color = Color.Black,
//            style = Stroke(4.dp.toPx())
//        )


//        val path = Path().apply {
//            moveTo(startOffset.x, startOffset.y)
//            lineTo(endOffset.x, endOffset.y)
//            close()
//
//            var xDir = if (startOffset.y - endOffset.y > 0) endOffset.y + 50
//            else endOffset.y - 50
//
//            moveTo(endOffset.x, endOffset.y)
//            lineTo(endOffset.x + 50, xDir)
//            close()
//            moveTo(endOffset.x, endOffset.y)
//            lineTo(endOffset.x - 50, xDir)
//
//            close()
//        }
//
//        drawPath(path, color = Color.Black, style = Stroke(4.dp.toPx()))

        drawLine(
            color = Color.Transparent,
            start = startOffset,
            end = endOffset,
        )
//        drawOval(
//            color = Color.Black,
//            topLeft = startOffset,
//            size = Size(
//                endOffset.x - startOffset.x,
//                endOffset.y - startOffset.y,
//            ),
//            style = Stroke(
//                width = 4.dp.toPx()
//            )
//
//        )
        for (drawing in drawings) {

//            if(drawing is Rectangle) {
//                drawRoundRect(
//                    color = Color.Black,
//                    topLeft = drawing.startOffset,
//                    size = drawing.size,
//                    style = Stroke(
//                        width = 4.dp.toPx()
//                    ),
//                    cornerRadius = CornerRadius(10f, 10f)
//                )
//
//            }
//            else if(drawing is Ellipse) {
//                drawOval(
//                    color = Color.Black,
//                    topLeft = drawing.startOffset,
//                    size = drawing.size,
//                    style = Stroke(
//                        width = 4.dp.toPx()
//                    )
//
//                )
//
//            }
            when (drawing) {
                is Rectangle -> (drawing).draw(this)
//                    drawRoundRect(
//                        color = Color.Black,
//                        topLeft = drawing.startOffset,
//                        size = drawing.size,
//                        style = Stroke(
//                            width = 4.dp.toPx()
//                        ),
//                        cornerRadius = CornerRadius(10f, 10f)
//                    )

                is Ellipse -> drawing.draw(this)
            }
        }

    }
}
