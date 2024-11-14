package com.chani.arcifycircleindicatorcompose

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chani.arcifycircleindicator.ArcifyCircleIndicator
import com.chani.arcifycircleindicator.ArcifyInfiniteIndicator
import kotlinx.coroutines.delay

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navController = LocalNavController.current

    Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Button(
                onClick = { navController.navigate("infinite") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "infinite")
            }

            Button(
                onClick = { navController.navigate("basic") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "basic")
            }
        }
    }
}