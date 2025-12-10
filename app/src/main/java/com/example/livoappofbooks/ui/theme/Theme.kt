package com.example.livoappofbooks.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(

    // --- Primary ---
    primary = PrincipalColor,               // mantém verde como identidade
    onPrimary = BackgroundLight,            // texto claro sobre o verde
    primaryContainer = PrincipalColor,
    onPrimaryContainer = BackgroundLight,

    // --- Secondary ---
    secondary = BackgroundDark,             // tons escuros para itens
    onSecondary = BackgroundLight,
    secondaryContainer = BackgroundDark,
    onSecondaryContainer = BackgroundLight,

    // --- Tertiary ---
    tertiary = BackgroundLight,             // elementos claros (títulos, chips)
    onTertiary = DarkColor,                 // texto escuro sobre fundo claro
    tertiaryContainer = BackgroundLight,
    onTertiaryContainer = DarkColor,

    // --- Background / Surface ---
    background = BackgroundDark,            // 0xFF121517
    onBackground = BackgroundLight,         // 0xFFFDFBED

    surface = BackgroundDark,               // cards e navegadores
    onSurface = BackgroundLight,

    surfaceVariant = BackgroundDark,
    onSurfaceVariant = BackgroundLight,

    surfaceTint = PrincipalColor,

    // --- Outline / Dividers ---
    outline = BackgroundLight,
    outlineVariant = Gray,

    // --- Errors ---
    error = AlertColor,
    onError = BackgroundLight,

    // --- Inverted roles ---
    inversePrimary = Gray,
    inverseSurface = BackgroundLight,
    inverseOnSurface = DarkColor
)


private val LightColorScheme = lightColorScheme(

    // --- Primary ---
    primary = PrincipalColor,               // verde
    onPrimary = BackgroundLight,
    primaryContainer = PrincipalColor,
    onPrimaryContainer = BackgroundLight,

    // --- Secondary ---
    secondary = BackgroundLight,
    onSecondary = DarkColor,
    secondaryContainer = BackgroundLight,
    onSecondaryContainer = DarkColor,

    // --- Tertiary ---
    tertiary = DarkColor,
    onTertiary = BackgroundLight,
    tertiaryContainer = DarkColor,
    onTertiaryContainer = BackgroundLight,

    // --- Background / Surface ---
    background = BackgroundLight,
    onBackground = BackgroundDark,

    surface = White,
    onSurface = Black,

    surfaceVariant = BackgroundLight,
    onSurfaceVariant = DarkColor,

    surfaceTint = PrincipalColor,

    // --- Outline / Dividers ---
    outline = PrincipalColor,
    outlineVariant = Gray,

    // --- Errors ---
    error = AlertColor,
    onError = BackgroundLight,

    // --- Inverted roles ---
    inversePrimary = DarkColor,
    inverseSurface = DarkColor,
    inverseOnSurface = BackgroundLight
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
val outline: Color
    get() = MaterialTheme.colorScheme.outline

@get:Composable
val error: Color
    get() = MaterialTheme.colorScheme.error

@get:Composable
val onBackground: Color
    get() = MaterialTheme.colorScheme.onBackground

@get:Composable
val inversePrimary: Color
    get() = MaterialTheme.colorScheme.inversePrimary
