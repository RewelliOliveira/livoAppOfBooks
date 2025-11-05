package com.example.livoappofbooks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.livoappofbooks.ui.screens.LibraryScreen
import com.example.livoappofbooks.ui.theme.ThemeProvider
import com.example.livoappofbooks.ui.theme.rememberThemeState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            ThemeProvider {
                ConfigureSystemBarsForTheme()
                LibraryScreen()
            }
        }
    }
}

@Composable
fun ConfigureSystemBarsForTheme() {
    val view = LocalView.current
    val themeState = rememberThemeState()

    LaunchedEffect(themeState.isDarkTheme) {
        val window = (view.context as ComponentActivity).window

        WindowCompat.getInsetsController(window, view).apply {
            // Ícones pretos no modo claro, brancos no modo escuro
            isAppearanceLightStatusBars = !themeState.isDarkTheme
            isAppearanceLightNavigationBars = !themeState.isDarkTheme
        }
    }
}