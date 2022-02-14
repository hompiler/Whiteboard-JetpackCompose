package com.hompiler.whiteboard.ui.features.whiteboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hompiler.whiteboard.models.DrawingTool
import com.hompiler.whiteboard.ui.theme.Selected

@Composable
fun ToolbarButton(
    active: Boolean = false,
    onClick: () -> Unit,
    IconComposable: @Composable () -> Unit,
) {

    val commonModifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .padding(10.dp, 0.dp)


    CommonRowButton(
        commonModifier = commonModifier,
        activeModifier = commonModifier.clip(RoundedCornerShape(10.dp)).background(Selected),
        active = active,
        onClick = onClick
    ) {
        IconComposable()
    }

}

@Composable
fun DrawingToolbarButton(
    selected: Boolean = false,
    tool: DrawingTool,
    onToolSelect: (DrawingTool) -> Unit = {},
) {

    ToolbarButton(
        active = selected,
        onClick = { onToolSelect(tool) }
    ) {
        Icon(
            painterResource(tool.iconResource),
            tool.contentDescription,
            tint = MaterialTheme.colors.onSurface,
        )
    }

}

@Composable
fun DrawingToolsToolbar(
    selectedTool: DrawingTool,
    tools: List<DrawingTool>,
    onToolSelect: (DrawingTool) -> Unit,
    modifier: Modifier = Modifier,
    penOptions: @Composable () -> Unit,
) {

    CommonRow(modifier) {
        for (tool in tools) {
            DrawingToolbarButton(
                selected = tool == selectedTool,
                tool = tool,
                onToolSelect = onToolSelect,
            )
        }

        penOptions()
    }
}


@Preview
@Composable
fun ToolbarPreview() {
//    ToolsToolbar(tools = tools, selectedTool = selectedTool, {},)
}