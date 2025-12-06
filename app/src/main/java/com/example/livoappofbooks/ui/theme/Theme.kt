package com.example.livoappofbooks.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = BackgroundLight,
    secondary = PrincipalColor,
    tertiary = BackgroundLight,
    background = BackgroundDark,
    surface = ProgressBarDark,
    error = AlertColor,
    onBackground = BackgroundLight
)

private val LightColorScheme = lightColorScheme(
    primary = PrincipalColor,
    secondary = BackgroundLight,
    tertiary = DarkColor,
    background = BackgroundLight,
    surface = ProgressBarLight,
    error = AlertColor,
    onBackground = BackgroundDark
)

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

@get:Composable
val primary: Color
    get() = MaterialTheme.colorScheme.primary

@get:Composable
val secondary: Color
    get() = MaterialTheme.colorScheme.secondary

@get:Composable
val tertiary: Color
    get() = MaterialTheme.colorScheme.tertiary

@get:Composable
val background: Color
    get() = MaterialTheme.colorScheme.background

@get:Composable
val surface: Color
    get() = MaterialTheme.colorScheme.surface

@get:Composable
val error: Color
    get() = MaterialTheme.colorScheme.error

@get:Composable
val onBackground: Color
    get() = MaterialTheme.colorScheme.onBackground
