package com.example.livoappofbooks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.livoappofbooks.data.ThemePreferences
import com.example.livoappofbooks.data.ThemeRepository
import com.example.livoappofbooks.navigation.AppNavigation
import com.example.livoappofbooks.ui.screens.InitialScreen
import com.example.livoappofbooks.ui.screens.LoginScreen
import com.example.livoappofbooks.ui.screens.RegisterScreen
import com.example.livoappofbooks.ui.theme.ThemeProvider
import com.example.livoappofbooks.viewmodel.ThemeViewModel
import com.example.livoappofbooks.viewmodel.ThemeViewModelFactory

class MainActivity : ComponentActivity() {

    private val themeViewModel: ThemeViewModel by viewModels {
        ThemeViewModelFactory(
            ThemeRepository(ThemePreferences(this))
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val isDark by themeViewModel.isDarkTheme.collectAsState()

            ThemeProvider(
                isDarkTheme = isDark,
                toggleTheme = { themeViewModel.toggleTheme() }
            ) {
                ConfigureSystemBarsForTheme(isDark)
                RootNavigation(themeViewModel)
            }
        }
    }
}

@Composable
fun RootNavigation(themeViewModel: ThemeViewModel) {
    var currentScreen by remember { mutableStateOf("initial") }

    when (currentScreen) {
        "initial" -> InitialScreen(
            title = "Organize suas leituras com o Livo!",
            subTitle = "Selecione uma das opções para continuar",
            onLoginClick = { currentScreen = "login" },
            onRegisterClick = { currentScreen = "register" }
        )
        "login" -> LoginScreen(
            onLoginSuccess = { currentScreen = "app" },
            onBackClick = { currentScreen = "initial" }
        )
        "register" -> RegisterScreen(
            onBackClick = { currentScreen = "initial" },
            onRegisterComplete = { currentScreen = "initial" }
        )
        "app" -> AppNavigation(themeViewModel)
    }
}

@Composable
fun ConfigureSystemBarsForTheme(isDark: Boolean) {
    val view = LocalView.current

    LaunchedEffect(isDark) {
        val window = (view.context as ComponentActivity).window
        WindowCompat.getInsetsController(window, view)?.apply {
            isAppearanceLightStatusBars = !isDark
            isAppearanceLightNavigationBars = !isDark
        }
    }
}
