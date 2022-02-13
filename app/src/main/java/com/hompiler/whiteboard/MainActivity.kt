package com.hompiler.whiteboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.hompiler.whiteboard.ui.features.whiteboard.Whiteboard
import com.hompiler.whiteboard.ui.theme.WhiteboardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WhiteboardTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    App()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun App() {
    Whiteboard()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WhiteboardTheme {
        Whiteboard()
    }
}