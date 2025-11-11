package com.example.livoappofbooks.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

// 🌙 Tema Escuro
private val DarkColorScheme = darkColorScheme(
    primary = PrincipalColor,
    secondary = PositiveActions,
    tertiary = SubtitlesColor,
    background = DarkColor,
    surface = DarkColor,
    error = AlertColor,
    onPrimary = BackgroundLight,
    onSecondary = BackgroundLight,
    onTertiary = BackgroundLight,
    onBackground = BackgroundLight,
    onSurface = BackgroundLight
)

// 🌞 Tema Claro
private val LightColorScheme = lightColorScheme(
    primary = PrincipalColor,
    secondary = PositiveActions,
    tertiary = SubtitlesColor,
    background = BackgroundLight,
    surface = BackgroundLight,
    error = AlertColor,
    onPrimary = BackgroundLight,
    onSecondary = BackgroundLight,
    onTertiary = DarkColor,
    onBackground = DarkColor,
    onSurface = DarkColor
)

@Composable
fun LivoAppOfBooksTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        shapes = Shapes,
        content = content
    )
}
