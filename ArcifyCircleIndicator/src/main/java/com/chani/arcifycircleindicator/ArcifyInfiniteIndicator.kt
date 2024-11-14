package com.chani.arcifycircleindicator

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ArcifyInfiniteIndicator(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
    backgroundColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
    strokeWidth: Dp = 6.dp,
    strokeCap: StrokeCap = StrokeCap.Round,
    progressDirection: ArcifyProgressDirection = ArcifyProgressDirection.CLOCKWISE,
    animationDuration: Int = 1000,
    centerContent: @Composable (() -> Unit)? = null
) {
    val infiniteTransition = rememberInfiniteTransition()

    val animatedStartAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = animationDuration, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = ""
    )

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val stroke = strokeWidth.toPx()
            val adjustedStartAngle = if (progressDirection == ArcifyProgressDirection.CLOCKWISE) {
                animatedStartAngle
            } else {
                -animatedStartAngle
            }

            // Background arc
            drawArc(
                color = backgroundColor,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(stroke, cap = strokeCap)
            )

            // Infinite progress arc
            drawArc(
                color = color,
                startAngle = adjustedStartAngle,
                sweepAngle = 90f,
                useCenter = false,
                style = Stroke(stroke, cap = strokeCap)
            )
        }
        centerContent?.invoke()
    }
}