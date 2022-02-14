package com.hompiler.whiteboard.ui.features.whiteboard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.hompiler.whiteboard.R


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Whiteboard(viewModel: WhiteboardViewModel) {


    val selectedTool = viewModel.selectedTool.value

    var isColorSelectorOpen by rememberSaveable {
        mutableStateOf(false)
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (toolbar, canvas, colorPicker) = createRefs()


        WhiteboardCanvas(
            selectedTool = selectedTool,
            selectedColor = viewModel.selectedColor.value,
            drawings = viewModel.drawings.value,
            addDrawable = viewModel::addDrawable,
            updateDrawable = viewModel::updateDrawable,
            modifier = Modifier
                .constrainAs(canvas) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        DrawingToolsToolbar(
            tools = viewModel.tools,
            selectedTool = selectedTool,
            onToolSelect = { viewModel.selectedTool.value = it },
            modifier = Modifier
                .constrainAs(toolbar) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                }
        ) {
            ToolbarButton(
                active = isColorSelectorOpen,
                onClick = { isColorSelectorOpen = !isColorSelectorOpen }
            ) {
                Icon(
                    painterResource(R.drawable.ic_color_outline),
                    "Color Selector",
                    tint = MaterialTheme.colors.onSurface,
                )
            }

            ToolbarButton(onClick = { viewModel.clearCanvas() }) {
                Icon(
                    Icons.Outlined.Delete,
                    "Color Selector",
                    tint = MaterialTheme.colors.onSurface,
                )
            }
        }

        AnimatedVisibility(
            isColorSelectorOpen,
            modifier = Modifier.constrainAs(colorPicker) {
                top.linkTo(toolbar.bottom, margin = 8.dp)
                end.linkTo(toolbar.end)
                start.linkTo(toolbar.start)
                linkTo(toolbar.start, toolbar.end, bias = 1f)
            }
        ) {
            ColorPicker(
                colors = viewModel.colors,
                selectedColor = viewModel.selectedColor.value,
                onColorSelect = { viewModel.selectedColor.value = it },
            )
        }
    }
}