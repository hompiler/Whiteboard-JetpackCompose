package com.hompiler.whiteboard.ui.features.whiteboard

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hompiler.whiteboard.models.DrawingTool

@Composable
fun ToolbarButton(
    active: Boolean = false,
    onClick: () -> Unit,
    IconComposable: @Composable () -> Unit,
) {

    val backgroundColor by animateColorAsState(
        if (active) Color.LightGray
        else Color.Transparent
    )

    val clipSize by animateDpAsState(
        if (active) 10.dp
        else 50.dp
    )

    val alphaValue by animateFloatAsState(
        if(active) 1f else 0.7f
    )

    val commonModifier = Modifier
        .padding(10.dp, 0.dp)
        .clip(RoundedCornerShape(clipSize))
        .background(backgroundColor)
        .alpha(alphaValue)


    CommonRowButton(
        commonModifier = commonModifier,
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
            modifier = Modifier.clip(RoundedCornerShape(5.dp))
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

