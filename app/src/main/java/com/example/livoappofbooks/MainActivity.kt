package com.example.livoappofbooks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.livoappofbooks.navigation.AppNavigation
import com.example.livoappofbooks.ui.screens.InitialScreen
import com.example.livoappofbooks.ui.screens.LoginScreen
import com.example.livoappofbooks.ui.screens.RegisterScreen
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
                RootNavigation()
            }
        }
    }
}

@Composable
fun RootNavigation() {
    var currentScreen by remember { mutableStateOf("initial") }

    when (currentScreen) {
        "initial" -> InitialScreen(
            title = "Organize suas leituras com o Livo!",
            subTitle = "Selecione uma das opções para continuar",
            onLoginClick = { currentScreen = "login" },
            onRegisterClick = { currentScreen = "register" })

        "login" -> LoginScreen(
            onLoginSuccess = { currentScreen = "app" },
            onBackClick = { currentScreen = "initial" }
        )

        "register" -> RegisterScreen(
            onBackClick = { currentScreen = "initial" },
            onRegisterComplete = { currentScreen = "initial" }
        )

        "app" -> AppNavigation()
    }
}

@Composable
fun ConfigureSystemBarsForTheme() {
    val view = LocalView.current
    val themeState = rememberThemeState()

    LaunchedEffect(themeState.isDarkTheme) {
        val window = (view.context as ComponentActivity).window

        WindowCompat.getInsetsController(window, view).apply {
            isAppearanceLightStatusBars = !themeState.isDarkTheme
            isAppearanceLightNavigationBars = !themeState.isDarkTheme
        }
    }
}