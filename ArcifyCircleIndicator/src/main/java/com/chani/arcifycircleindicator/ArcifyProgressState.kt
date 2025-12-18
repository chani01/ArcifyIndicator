package com.chani.arcifycircleindicator

sealed class  ArcifyProgressState {
    data class Manual(val progress: Float) : ArcifyProgressState()
    data class Auto(val autoProgressDelay: Long = 500L) : ArcifyProgressState()
    data class TimeBased(val duration: Long = 5000L) : ArcifyProgressState()
}