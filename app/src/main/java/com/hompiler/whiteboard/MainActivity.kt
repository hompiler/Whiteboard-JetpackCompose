package com.hompiler.whiteboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.hompiler.whiteboard.ui.features.whiteboard.Whiteboard
import com.hompiler.whiteboard.ui.features.whiteboard.WhiteboardViewModel
import com.hompiler.whiteboard.ui.theme.WhiteboardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val whiteboardViewModel by viewModels<WhiteboardViewModel>();

        setContent {
            WhiteboardTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    App(whiteboardViewModel)
                }
            }
        }
    }
}


@Composable
fun App(whiteboardViewModel: WhiteboardViewModel) {
    Whiteboard(whiteboardViewModel)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WhiteboardTheme {
//        Whiteboard()
    }
}