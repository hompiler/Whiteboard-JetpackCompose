package com.hompiler.whiteboard.ui.features.whiteboard

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout


@Composable
fun Whiteboard(viewModel: WhiteboardViewModel) {


    val selectedTool = viewModel.selectedTool.value


    ConstraintLayout(
        modifier = Modifier
//            .fillMaxHeight()
            .fillMaxSize()
    ) {
        val (toolbar, canvas) = createRefs()


        WhiteboardCanvas(
            selectedTool = selectedTool,
            modifier = Modifier
                .constrainAs(canvas) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        ToolsToolbar(
            tools = viewModel.tools,
            selectedTool = selectedTool,
            onToolSelect = { viewModel.selectedTool.value = it },
            modifier = Modifier
                .constrainAs(toolbar) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun WhiteboardPreview() {
//    Whiteboard()
}