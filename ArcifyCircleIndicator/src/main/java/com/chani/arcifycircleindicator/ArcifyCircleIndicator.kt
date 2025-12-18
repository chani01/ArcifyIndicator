package com.chani.arcifycircleindicator

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

/**
 *  • modifier: Modifier to configure the size and layout of the composable.
 *  • progressState: Defines the progress state and mode.
 *      - Manual(progress: Float): Manual progress.
 *      - Auto(autoProgressDelay: Long): Sets the interval for automatic progress.
 *  • color: The color of the animated progress arc.
 *  • backgroundColor: The color of the unprogressed background arc.
 *  • strokeWidth: The thickness of the circular progress bar.
 *  • strokeCap: The shape of the ends of the progress bar line (Butt or Round).
 *  • progressDirection: The direction of progress (CLOCKWISE or COUNTERCLOCKWISE).
 *  • animationSpec: Configures the animation for progress (duration and easing).
 *  • centerContent: Custom composable content to display at the center (optional).
 *  • onProgressChanged: Callback triggered when the progress value changes (optional).
 */

@Composable
fun ArcifyCircleIndicator(
    modifier: Modifier = Modifier,
    progressState: ArcifyProgressState = ArcifyProgressState.Manual(0f),
    color: Color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
    backgroundColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
    strokeWidth: Dp = 6.dp,
    strokeCap: StrokeCap = StrokeCap.Round,
    progressDirection: ArcifyProgressDirection = ArcifyProgressDirection.CLOCKWISE,
    animationSpec: AnimationSpec<Float>? = tween(
        durationMillis = 1000,
        easing = LinearEasing
    ),
    centerContent: @Composable (() -> Unit)? = null,
    onProgressChanged: ((Float) -> Unit)? = null
) {

    var currentProgress by remember { mutableFloatStateOf(0f) }

    when (progressState) {
        is ArcifyProgressState.Auto -> {
            LaunchedEffect(progressState) {
                currentProgress = 0f
                val safeDelay = progressState.autoProgressDelay.coerceAtLeast(1L)
                while (true) {
                    delay(safeDelay)
                    currentProgress = 1.0f
                }
            }
        }
        is ArcifyProgressState.Manual -> {
            LaunchedEffect(progressState.progress) {
                currentProgress = progressState.progress
            }
        }
        is ArcifyProgressState.TimeBased -> {
            var startTime by remember { mutableLongStateOf(System.currentTimeMillis()) }
            var currentDuration by remember { mutableLongStateOf(progressState.duration) }
            
            LaunchedEffect(progressState.duration) {
                // duration이 변경되면 현재 progress와 경과 시간을 기반으로 startTime 재조정
                if (progressState.duration != currentDuration && currentProgress > 0f && currentProgress < 1.0f) {
                    val elapsedTime = (currentProgress * currentDuration).toLong()
                    startTime = System.currentTimeMillis() - elapsedTime
                }
                currentDuration = progressState.duration
            }
            
            LaunchedEffect(Unit) {
                val updateInterval = 16L  // ~60fps
                
                while (true) {
                    delay(updateInterval)
                    val elapsedTime = System.currentTimeMillis() - startTime
                    currentProgress = (elapsedTime.toFloat() / currentDuration).coerceAtMost(1.0f)
                    
                    if (currentProgress >= 1.0f) {
                        currentProgress = 1.0f
                        delay(1000)
                        startTime = System.currentTimeMillis()
                        currentProgress = 0f
                    }
                }
            }
        }
    }

    val animatedProgress by animateFloatAsState(
        targetValue = currentProgress,
        animationSpec = animationSpec ?: tween(0),
        label = "arcfiyCircleIndicator"
    )

    LaunchedEffect(animatedProgress) {
        onProgressChanged?.invoke(animatedProgress)
    }

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Canvas (modifier = Modifier.matchParentSize()) {
            val stroke = strokeWidth.toPx()
            val startAngle = -90f

            drawArc(
                color = backgroundColor,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(stroke, cap = strokeCap)
            )

            drawArc(
                color = color,
                startAngle = startAngle,
                sweepAngle = 360 * animatedProgress * if (progressDirection == ArcifyProgressDirection.CLOCKWISE) 1 else -1,
                useCenter = false,
                style = Stroke(stroke, cap = strokeCap)
            )
        }
        centerContent?.invoke()
    }
}