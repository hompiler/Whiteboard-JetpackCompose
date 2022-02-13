package com.hompiler.whiteboard.ui.features.whiteboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.hompiler.whiteboard.R
import com.hompiler.whiteboard.models.*

class WhiteboardViewModel() : ViewModel() {

    var tools = mutableStateListOf(
        DrawingTool(
            iconResource = R.drawable.ic_pen_outline,
            contentDescription = "Pencil",
            use = {
                Pencil(
                    startOffset = it,
                )
            }
        ),
        DrawingTool(
            iconResource = R.drawable.ic_arrow_outline,
            contentDescription = "Arrow",
            use = {
                Arrow(
                    startOffset = it,
                )
            }
        ),
        DrawingTool(
            iconResource = R.drawable.ic_square_outline,
            contentDescription = "Square",
            use = {
                Rectangle(
                    startOffset = it,
                )
            }
        ),
        DrawingTool(
            iconResource = R.drawable.ic_circle_outline,
            contentDescription = "Circle",
            use = {
                Ellipse(
                    startOffset = it,
                )
            }
        ),
        DrawingTool(
            iconResource = R.drawable.ic_color_outline,
            contentDescription = "Colors",
            use = {
                Ellipse(
                    startOffset = it,
                )
            }
        ),
    )
        private set

    var selectedTool =
    mutableStateOf(
        tools[0]
    )

}