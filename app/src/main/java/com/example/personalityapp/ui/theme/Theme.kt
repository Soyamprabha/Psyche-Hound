package com.example.personalityapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    background = WhiteBg,
    primary = Grey,
    surface = Green,
    onSurface = Black,
    secondary = Color.White,
)

private val DarkColorPalette = darkColors(
    background = Black,
    primary = Green,
    surface = Yellow,
    onSurface = WhiteBg,
    secondary = Color.Black,
)

@Composable
fun PersonalityAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}