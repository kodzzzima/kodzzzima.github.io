package ru.kodzzzima.kodzzzimaweb.content

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@Composable
fun App() {
    MaterialTheme {
        Scaffold(
            bottomBar = { BottomBar() }
        ) {
            MainContent()
        }
    }
}