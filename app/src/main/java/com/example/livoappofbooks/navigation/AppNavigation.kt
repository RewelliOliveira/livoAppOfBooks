package com.example.livoappofbooks.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.livoappofbooks.data.repository.LibraryRepository
import com.example.livoappofbooks.data.remote.RetrofitInstance
import com.example.livoappofbooks.data.service.LibraryService
import com.example.livoappofbooks.ui.viewModel.ThemeViewModel
import com.example.livoappofbooks.ui.screens.*

@Composable
fun AppNavigation(themeViewModel: ThemeViewModel) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val context = LocalContext.current

    val libraryService = remember {
        RetrofitInstance.createService(context, LibraryService::class.java)
    }

    val libraryRepository = remember {
        LibraryRepository(libraryService)
    }

    Scaffold(
        bottomBar = {
            if (currentRoute in listOf(
                    Screen.Library.route,
                    Screen.Search.route,
                    Screen.Profile.route,
                    Screen.Shelfs.route
                )) {
                BottomBar(navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Library.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Library.route) {
                LibraryScreen(
                    context = context,
                    onBookClick = { book ->
                        navController.navigate("${Screen.ViewBook.route}/${book.id}")
                    }
                )
            }

            composable(Screen.Search.route) {
                SearchScreen(
                    onNavigate = { navController.navigate(Screen.Search.route) }
                )
            }

            composable(Screen.Profile.route) {
                ProfileScreen(
                    onNavigate = { navController.navigate(Screen.Library.route) },
                    themeViewModel = themeViewModel
                )
            }

            composable(Screen.Shelfs.route) {
                ShelfsScreen(
                    onShelfClick = { /* TODO */ },
                    onAddShelfClick = { /* TODO */ }
                )
            }

            composable(
                route = "${Screen.ViewBook.route}/{bookId}",
                arguments = listOf(navArgument("bookId") { type = NavType.StringType })
            ) { backStackEntry ->
                val bookId = backStackEntry.arguments?.getString("bookId")
                if (bookId != null) {
                    ViewBookScreen(
                        bookId = bookId,
                        repository = libraryRepository,
                        onBackClick = { navController.popBackStack() },
                        onRegisterClick = { navController.navigate(Screen.RegisterReading.route) }
                    )
                }
            }

            composable(Screen.RegisterReading.route) {
                RegisterReadingScreen(
                    onNavigate = { navController.popBackStack() }
                )
            }
        }
    }
}