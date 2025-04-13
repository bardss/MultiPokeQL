package com.jakubaniola.multipokeql.designsystem

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = Color(0xFFA7C7E7), // Pastel Blue
    primaryVariant = Color(0xFF6FAED9), // Soft Blue
    secondary = Color(0xFFFDFD96), // Pastel Yellow
    background = Color(0xFFF8F9FA), // Pastel Light Gray
    surface = Color(0xFFFFFFFF), // White
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
)

private val DarkColorPalette = darkColors(
    primary = Color(0xFF5A8BB0), // Deep Pastel Blue
    primaryVariant = Color(0xFF3A6A8A), // Darker Blue
    secondary = Color(0xFFF3E88E), // Soft Yellow
    background = Color(0xFF2C2F33), // Dark Pastel Gray
    surface = Color(0xFF121212), // Dark Gray
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White,
)

@Composable
fun AppTheme(
    darkTheme: Boolean = false, // You can toggle this based on system settings
    content: @Composable () -> Unit
) {
    val colors: Colors = if (darkTheme) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colors = colors,
        typography = MaterialTheme.typography,
        shapes = MaterialTheme.shapes,
        content = content
    )
}