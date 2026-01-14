package com.example.livoappofbooks

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.livoappofbooks.data.ThemePreferences
import com.example.livoappofbooks.data.ThemeRepository
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.livoappofbooks.navigation.AppNavigation
import com.example.livoappofbooks.ui.screens.InitialScreen
import com.example.livoappofbooks.ui.screens.LoginScreen
import com.example.livoappofbooks.ui.screens.RegisterScreen
import com.example.livoappofbooks.ui.theme.ThemeProvider
import com.example.livoappofbooks.ui.viewModel.ThemeViewModel
import com.example.livoappofbooks.ui.viewModel.ThemeViewModelFactory
import com.example.livoappofbooks.utils.NotificationScheduler
import com.example.livoappofbooks.utils.NotificationService

class MainActivity : ComponentActivity() {

    private val themeViewModel: ThemeViewModel by viewModels {
        ThemeViewModelFactory(
            ThemeRepository(ThemePreferences(this))
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val notificationService = NotificationService(applicationContext) //notificaçãozinha
        notificationService.createNotificationChannel()

        val prefs = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val isNotificationsEnabled = prefs.getBoolean("notifications_enabled", false) // true é o padrão se nunca escolheu

        if (isNotificationsEnabled) {
            NotificationScheduler.schedulePeriodicReminder(applicationContext)
        } else {
            NotificationScheduler.cancelReminder(applicationContext)
        }
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

        composable(
            route = "login?show_success_snackbar={show_success_snackbar}",
            arguments = listOf(navArgument("show_success_snackbar") {
                type = NavType.BoolType
                defaultValue = false
            })
        ) { backStackEntry ->
            val showSnackbar = backStackEntry.arguments?.getBoolean("show_success_snackbar") ?: false
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("app") {
                        popUpTo("initial") { inclusive = true }
                    }
                },
                onBackClick = { navController.popBackStack() },
                showSuccessSnackbar = showSnackbar
            )
        }

        composable("register") {
            RegisterScreen(
                onBackClick = { navController.popBackStack() },
                onRegisterComplete = {
                    navController.navigate("login?show_success_snackbar=true") {
                        popUpTo("initial")
                    }
                }
            )
        }

        composable("app") {
            AppNavigation(themeViewModel)
        }
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
