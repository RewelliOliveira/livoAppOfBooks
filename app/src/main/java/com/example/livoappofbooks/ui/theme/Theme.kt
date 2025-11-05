package com.example.livoappofbooks.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// 🌙 Tema escuro
private val DarkColorScheme = darkColorScheme(
    primary = PrincipalColor,
    secondary = PositiveActions,
    tertiary = SubtitlesColor,
    background = DarkColor,
    surface = DarkColor,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White
)

// 🌞 Tema claro
private val LightColorScheme = lightColorScheme(
    primary = PrincipalColor,
    secondary = PositiveActions,
    tertiary = SubtitlesColor,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black
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
    content: @Composable () -> Unit
) {
    var isDarkTheme by remember { mutableStateOf(false) }

    val themeState = ThemeState(
        isDarkTheme = isDarkTheme,
        toggleTheme = { isDarkTheme = !isDarkTheme }
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
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}