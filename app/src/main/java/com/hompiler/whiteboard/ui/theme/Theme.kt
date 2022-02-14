package com.hompiler.whiteboard.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Orange200,
    primaryVariant = Orange700,
    secondary = Petrol
)

private val LightColorPalette = lightColors(
    primary = Orange500,
    primaryVariant = Orange700,
    secondary = Petrol,

    background = Color.White,
    surface = Color(0xFFF4F4F4),
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,


    )

@Composable
fun WhiteboardTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        LightColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content,
    )
}