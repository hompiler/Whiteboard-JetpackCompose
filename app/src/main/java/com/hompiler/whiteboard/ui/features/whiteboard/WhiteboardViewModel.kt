package com.hompiler.whiteboard.ui.features.whiteboard

import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.hompiler.whiteboard.R
import com.hompiler.whiteboard.models.*

class WhiteboardViewModel() : ViewModel() {

    var colors = mutableStateListOf(
        Color.Red,
        Color(0xFF007F00),
        Color(0xFF0078DE),
        Color.Black,
    )
        private set

    var selectedColor =
        mutableStateOf(
            colors.last()
        )

    var tools = mutableStateListOf(
        DrawingTool(
            iconResource = R.drawable.ic_pen_outline,
            contentDescription = "Pencil",
            use = {
                Pencil(color = selectedColor.value)
            }
        ),
        DrawingTool(
            iconResource = R.drawable.ic_arrow_outline,
            contentDescription = "Arrow",
            use = {
                Arrow(
                    startOffset = it,
                    color = selectedColor.value
                )
            }
        ),
        DrawingTool(
            iconResource = R.drawable.ic_square_outline,
            contentDescription = "Square",
            use = {
                Rectangle(
                    startOffset = it,
                    color = selectedColor.value
                )
            }
        ),
        DrawingTool(
            iconResource = R.drawable.ic_circle_outline,
            contentDescription = "Ellipse",
            use = {
                Ellipse(
                    startOffset = it,
                    color = selectedColor.value
                )
            }
        ),

    )
        private set

    var selectedTool =
    mutableStateOf(
        tools[0]
    )

    var drawings =
        mutableStateOf(
            mutableListOf<Drawable>()
        )


    fun addDrawing(startOffset: Offset) {
        drawings.value.add(selectedTool.value.use(startOffset))
    }



}