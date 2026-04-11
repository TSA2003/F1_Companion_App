package com.example.formula1ai.ui.theme

import androidx.compose.material3.*
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color // Ensure this is imported

private val DarkColorScheme = darkColorScheme(
    primary = F1Red,
    background = DarkBackground,
    surface = CardBackground,
    onPrimary = Color.White,
    onBackground = LightGray
)

@Composable
fun F1Theme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography(),
        content = content
    )
}