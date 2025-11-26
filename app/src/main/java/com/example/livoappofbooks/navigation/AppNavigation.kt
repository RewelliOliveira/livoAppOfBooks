package com.example.livoappofbooks.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.livoappofbooks.ui.screens.LibraryScreen
import com.example.livoappofbooks.ui.screens.ProfileScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Screen.Library.route,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(Screen.Library.route) {
                LibraryScreen(
                    onNavigate = { navController.navigate(Screen.Profile.route) }
                )
            }

            composable(Screen.Profile.route) {
                ProfileScreen(
                    onNavigate = { navController.navigate(Screen.Library.route) }
                )
            }
        }
    }
}
