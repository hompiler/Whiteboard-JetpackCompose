package com.hompiler.whiteboard.ui.features.whiteboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hompiler.whiteboard.R
import com.hompiler.whiteboard.models.DrawingTool
import com.hompiler.whiteboard.ui.theme.Selected

@Composable
fun ToolbarButton(
    selected: Boolean = false,
    tool: DrawingTool,
    onToolSelect: (DrawingTool) -> Unit = {},
    commonModifier: Modifier = Modifier
        .padding(10.dp, 0.dp),
    activeModifier: Modifier = commonModifier
        .clip(RoundedCornerShape(10.dp))
        .background(Selected)

) {
    IconButton(
        modifier = if (selected) activeModifier else commonModifier.alpha(0.7f),
        onClick = { onToolSelect(tool) }
    ) {
        Icon(
            painterResource(tool.iconResource),
            tool.contentDescription,
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colors.onSurface,
        )
    }
}

@Composable
fun ToolsToolbar(
//    selectedTool: DrawingTool,
//    tools: List<DrawingTool>,
//    onToolSelect: (DrawingTool) -> Unit,
    modifier: Modifier = Modifier
) {

    var tools by remember {
        mutableStateOf(
            listOf(
                DrawingTool(
                    iconResource = R.drawable.ic_pen_outline,
                    contentDescription = "Pencil",
                ),
                DrawingTool(
                    iconResource = R.drawable.ic_arrow_outline,
                    contentDescription = "Arrow",
                ),
                DrawingTool(
                    iconResource = R.drawable.ic_square_outline,
                    contentDescription = "Square",
                ),
                DrawingTool(
                    iconResource = R.drawable.ic_circle_outline,
                    contentDescription = "Circle",
                ),
                DrawingTool(
                    iconResource = R.drawable.ic_color_outline,
                    contentDescription = "Colors",
                )
            )
        )
    }

    var selectedTool by remember {
        mutableStateOf(
            tools[0]
        )
    }

    Surface(
        color = MaterialTheme.colors.surface,
        modifier = modifier
            .alpha(0.7f)
            .clip(RoundedCornerShape(16.dp))
    ) {
        Row(modifier = Modifier.padding(10.dp)) {
            for (tool in tools) {
                ToolbarButton(
                    selected = tool == selectedTool,
                    tool = tool,
                    onToolSelect = {selectedTool = it},
                )
            }
        }
    }
}


@Preview
@Composable
fun ToolbarPreview() {
    var tools by remember {
        mutableStateOf(
            listOf(
                DrawingTool(
                    iconResource = R.drawable.ic_pen_outline,
                    contentDescription = "Pencil",
                ),
                DrawingTool(
                    iconResource = R.drawable.ic_arrow_outline,
                    contentDescription = "Arrow",
                ),
                DrawingTool(
                    iconResource = R.drawable.ic_square_outline,
                    contentDescription = "Square",
                ),
                DrawingTool(
                    iconResource = R.drawable.ic_circle_outline,
                    contentDescription = "Circle",
                ),
                DrawingTool(
                    iconResource = R.drawable.ic_color_outline,
                    contentDescription = "Colors",
                )
            )
        )
    }

    var selectedTool by remember {
        mutableStateOf(
            tools[0]
        )
    }
//    ToolsToolbar(tools = tools, selectedTool = selectedTool, {},)
}