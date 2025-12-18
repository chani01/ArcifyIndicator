package com.chani.arcifycircleindicatorcompose

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.chani.arcifycircleindicator.ArcifyCircleIndicator
import com.chani.arcifycircleindicator.ArcifyProgressState

@Composable
fun ProgressTimeScreen() {
    var animatedProgress by remember { mutableFloatStateOf(0f) }
    var duration by remember { mutableLongStateOf(5000L) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ArcifyCircleIndicator(
            modifier = Modifier.size(200.dp),
            progressState = ArcifyProgressState.TimeBased(duration = duration),
            strokeWidth = 8.dp,
            animationSpec = tween(
                durationMillis = 50,
                easing = LinearEasing
            ),
            strokeCap = StrokeCap.Round,
            centerContent = {
                Text("${(animatedProgress * 100).toInt()}%")
            },
            onProgressChanged = { animatedProgress = it }
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Text("Duration: ${duration}ms")
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { duration = 3000L }) {
                Text("3sec")
            }
            Button(onClick = { duration = 5000L }) {
                Text("5sec")
            }
            Button(onClick = { duration = 10000L }) {
                Text("10sec")
            }
        }
    }
}


@Composable
fun ProgressManualScreen() {
    var animatedProgress by remember { mutableFloatStateOf(0f) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ArcifyCircleIndicator(
            modifier = Modifier.size(200.dp),
            progressState = ArcifyProgressState.Manual(progress = 0.5f),
            strokeWidth = 8.dp,
            strokeCap = StrokeCap.Butt,
            animationSpec = null,
            centerContent = {
                Text("${(animatedProgress * 100).toInt()}%")
            },
            onProgressChanged = { animatedProgress = it }
        )
    }
}

@Composable
fun ProgressAutoScreen() {
    var animatedProgress by remember { mutableFloatStateOf(0f) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ArcifyCircleIndicator(
            modifier = Modifier.size(200.dp),
            progressState = ArcifyProgressState.Auto(0L),
            strokeWidth = 8.dp,
            strokeCap = StrokeCap.Butt,
            animationSpec = tween(
                durationMillis = 5000,
                easing = LinearEasing
            ),
            centerContent = {
                Text("${(animatedProgress * 100).toInt()}%")
            },
            onProgressChanged = { animatedProgress = it }
        )
    }
}