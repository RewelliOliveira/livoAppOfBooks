package com.example.livoappofbooks.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalContext

// 🌙 Tema Escuro
private val DarkColorScheme = darkColorScheme(
    primary = BackgroundLight,
    secondary = PrincipalColor,
    tertiary = BackgroundLight,
    background = BackgroundDark,
    surface = ProgressBarDark,
    error = AlertColor,
    //onPrimary = ProgressBarDark,
    //onSecondary = BackgroundLight,
    //onTertiary = BackgroundLight,
    onBackground = BackgroundLight,
    //onSurface = BackgroundLight
)

// 🌞 Tema Claro
private val LightColorScheme = lightColorScheme(
    primary = PrincipalColor,
    secondary = BackgroundLight,
    tertiary = DarkColor,
    background = BackgroundLight,
    surface = ProgressBarLight,
    error = AlertColor,
    //onPrimary = ProgressBarLight,
    //onSecondary = BackgroundLight,
    //onTertiary = PrincipalColor,
    onBackground = BackgroundDark,
    //onSurface = DarkColor
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
    dynamicColor: Boolean = false,
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