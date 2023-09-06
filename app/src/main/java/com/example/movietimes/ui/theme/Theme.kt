package com.example.movietimes.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

val AppInitialBackground = Brush.verticalGradient(
    colorStops = arrayOf(
        0.15f to Black,
        1f to MidBlue
    )
)

val AppBackground = Brush.verticalGradient(
    colorStops = arrayOf(
        0.55f to Black,
        1f to MidBlue
    )
)

val BottomBarBackground = Brush.verticalGradient(
    colorStops = arrayOf(
        0.15f to White25,
        0.8f to Black25
    )
)

@Composable
fun MovieTimesTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
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