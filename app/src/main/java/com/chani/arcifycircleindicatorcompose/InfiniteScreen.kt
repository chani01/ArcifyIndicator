package com.chani.arcifycircleindicatorcompose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chani.arcifycircleindicator.ArcifyInfiniteIndicator

@Composable
fun InfiniteScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        ArcifyInfiniteIndicator(modifier = Modifier.size(60.dp), animationDuration = 2000)
    }
}