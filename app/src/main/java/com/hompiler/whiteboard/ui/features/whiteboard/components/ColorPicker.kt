package com.hompiler.whiteboard.ui.features.whiteboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ColorButton(
    selected: Boolean = false,
    color: Color,
    onColorSelect: (Color) -> Unit,
) {

    val commonModifier = Modifier
        .padding(4.dp, 0.dp)
        .clip(RoundedCornerShape(10.dp))
        .background(color)

    CommonRowButton(
        commonModifier = commonModifier,
        onClick = { onColorSelect(color) }
    ) {
        Icon(
            Icons.Default.Check,
            "Color",
            modifier = if (selected) Modifier.alpha(1f) else Modifier.alpha(0f),
            tint = Color.White
        )
    }

}

@Composable
fun ColorPicker(
    selectedColor: Color,
    colors: List<Color>,
    onColorSelect: (Color) -> Unit,
    modifier: Modifier = Modifier,
) {

    CommonRow(modifier) {
        for (color in colors) {
            ColorButton(
                selected = color == selectedColor,
                color = color,
                onColorSelect = onColorSelect,
            )
        }
    }
}