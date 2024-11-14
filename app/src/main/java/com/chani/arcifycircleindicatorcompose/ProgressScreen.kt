package com.chani.arcifycircleindicatorcompose

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.chani.arcifycircleindicator.ArcifyCircleIndicator
import com.chani.arcifycircleindicator.ArcifyProgressState
import kotlinx.coroutines.delay

@Composable
fun ProgressScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        ArcifyCircleIndicator(
            modifier = Modifier.size(60.dp),
            progressState = ArcifyProgressState.Auto(),
            strokeWidth = 8.dp,
            animationSpec = tween(
                durationMillis = 5000,
                easing = LinearEasing
            ),
            strokeCap = StrokeCap.Butt,
            centerContent = {}
        )
    }
}