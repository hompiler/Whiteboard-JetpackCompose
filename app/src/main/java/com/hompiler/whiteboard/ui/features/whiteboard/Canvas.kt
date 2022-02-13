package com.hompiler.whiteboard.ui.features.whiteboard

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.hompiler.whiteboard.R
import com.hompiler.whiteboard.models.Drawing


@Composable
fun WhiteboardCanvas(
    modifier: Modifier = Modifier
) {

    var startOffset by remember { mutableStateOf<Offset>(Offset(-1.0f, -1.0f)) }
    var endOffset by remember { mutableStateOf<Offset>(Offset(-1.0f, -1.0f)) }
    var points by remember { mutableStateOf(
        mutableListOf<Offset>()
    )}

    var drawings by remember {
        mutableStateOf(
            mutableListOf<Drawing>()
        )
    }

    val pointerModifier = modifier
        .pointerInput(Unit) {
            forEachGesture {

                awaitPointerEventScope {

                    val down = awaitFirstDown()
                    // ACTION_DOWN here
                    startOffset = down.position

                    drawings.add(Drawing(startOffset))
                    points.add(down.position)

//                    Log.wtf("DOWN", "$startOffset")

                    do {

                        //This PointerEvent contains details including
                        // event, id, position and more
                        val event: PointerEvent = awaitPointerEvent()
                        endOffset = event.changes[0].position
                        Log.wtf("MOVE", "$endOffset")
                        points.add(endOffset)
                        drawings[drawings.lastIndex].size = Size(
                            endOffset.x - startOffset.x,
                            endOffset.y - startOffset.y,
                        )

//                        drawings[drawings.lastIndex].endOffset = event.changes[0].position
                        // ACTION_MOVE loop
                        // Consuming event prevents other gestures or scroll to intercept
                        event.changes.forEach { pointerInputChange: PointerInputChange ->
                            pointerInputChange.consumePositionChange()


                        }
                    } while (event.changes.any { it.pressed })

                    // ACTION_UP is here
                    Log.wtf("UP", "${drawings}")
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



        drawLine(
            color = Color.Transparent,
            start = startOffset,
            end = endOffset,
            strokeWidth = 4.dp.toPx()
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
            drawRect(
                color = Color.Black,
                topLeft = drawing.startOffset,
                size = drawing.size,
                style = Stroke(
                    width = 4.dp.toPx()
                )
            )
        }

    }
}
