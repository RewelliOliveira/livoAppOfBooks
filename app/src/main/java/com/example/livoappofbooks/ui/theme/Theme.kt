package com.example.livoappofbooks.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

// 🌙 Tema Escuro
private val DarkColorScheme = darkColorScheme(
    primary = BackgroundLight,
    secondary = PrincipalColor,
    tertiary = BackgroundLight,
    background = BackgroundDark,
    surface = ProgressBarDark,
    error = AlertColor,
    onBackground = BackgroundLight
)

// 🌞 Tema Claro
private val LightColorScheme = lightColorScheme(
    primary = PrincipalColor,
    secondary = BackgroundLight,
    tertiary = DarkColor,
    background = BackgroundLight,
    surface = ProgressBarLight,
    error = AlertColor,
    onBackground = BackgroundDark
)

// Gerenciamento do tema
data class ThemeState(
    val isDarkTheme: Boolean,
    val toggleTheme: () -> Unit
)

val LocalThemeState = compositionLocalOf<ThemeState> {
    error("ThemeState not provided")
}

@Composable
fun ThemeProvider(
    isDarkTheme: Boolean,
    toggleTheme: () -> Unit,
    content: @Composable () -> Unit
) {
    val themeState = ThemeState(
        isDarkTheme = isDarkTheme,
        toggleTheme = toggleTheme
    )

    CompositionLocalProvider(LocalThemeState provides themeState) {
        LivoAppOfBooksTheme(darkTheme = isDarkTheme) {
            content()
        }
    }
}

@Composable
fun rememberThemeState(): ThemeState {
    return LocalThemeState.current
}

@Composable
fun LivoAppOfBooksTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        shapes = Shapes,
        content = content
    )
}
