package com.hompiler.whiteboard.ui.features.whiteboard

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun CommonRowButton(
    commonModifier: Modifier = Modifier,
    activeModifier: Modifier = commonModifier,
    active: Boolean = false,
    onClick: () -> Unit,
    Icon: @Composable () -> Unit,
) {


    IconButton(
        modifier = if (active) activeModifier else commonModifier.alpha(0.7f),
        onClick = onClick
    ) {
        Icon()
    }
}



@Composable
fun CommonRow(
    rowModifier: Modifier = Modifier,
    ItemsComposable: @Composable () -> Unit
) {
    Surface(
        color = MaterialTheme.colors.surface,
        modifier = rowModifier
            .alpha(0.9f)
            .clip(RoundedCornerShape(16.dp))
    ) {
        Row(modifier = Modifier.padding(10.dp)) {
            ItemsComposable()
        }
    }
}