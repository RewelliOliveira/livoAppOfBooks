package com.example.livoappofbooks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "initial") {
        composable("initial") {
            InitialScreen(
                title = "Organize suas leituras com o Livo!",
                subTitle = "Selecione uma das opções para continuar",
                onLoginClick = { navController.navigate("login") },
                onRegisterClick = { navController.navigate("register") }
            )
        }
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("app") {
                        popUpTo("initial") { inclusive = true }
                    }
                },
                onBackClick = { navController.popBackStack() }
            )
        }
        composable("register") {
            RegisterScreen(
                onBackClick = { navController.popBackStack() },
                onRegisterComplete = { _, _, _ -> navController.popBackStack() }
            )
        }
        composable("app") {
            AppNavigation()
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
            isAppearanceLightStatusBars = !themeState.isDarkTheme
            isAppearanceLightNavigationBars = !themeState.isDarkTheme
        }
    }
}
