package com.techlads.devfestdemo.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color


fun Modifier.gradientOverlay(gradientColor: Color) = drawWithCache {
    val horizontalGradient = Brush.horizontalGradient(
        colors = listOf(
            gradientColor,
            Color.Transparent
        ),
        startX = size.width.times(0.2f),
        endX = size.width.times(0.7f)
    )
    val verticalGradient = Brush.verticalGradient(
        colors = listOf(
            Color.Transparent,
            gradientColor
        ),
        endY = size.width.times(0.3f)
    )
    val linearGradient = Brush.linearGradient(
        colors = listOf(
            gradientColor,
            Color.Transparent
        ),
        start = Offset(
            size.width.times(0.2f),
            size.height.times(0.5f)
        ),
        end = Offset(
            size.width.times(0.9f),
            0f
        )
    )

    onDrawWithContent {
        drawContent()
        drawRect(horizontalGradient)
        drawRect(verticalGradient)
        drawRect(linearGradient)
    }
}