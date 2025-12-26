package com.example.livoappofbooks.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.livoappofbooks.ui.viewModel.ThemeViewModel
import com.example.livoappofbooks.ui.screens.AddShelfScreen
import com.example.livoappofbooks.ui.screens.HistoryScreen
import com.example.livoappofbooks.ui.screens.InitialScreen
import com.example.livoappofbooks.ui.screens.LibraryScreen
import com.example.livoappofbooks.ui.screens.LoginScreen
import com.example.livoappofbooks.ui.screens.ProfileScreen
import com.example.livoappofbooks.ui.screens.RegisterReadingScreen
import com.example.livoappofbooks.ui.screens.RegisterScreen
import com.example.livoappofbooks.ui.screens.SearchScreen
import com.example.livoappofbooks.ui.screens.ShelfDetailsScreen
import com.example.livoappofbooks.ui.screens.ShelvesScreen
import com.example.livoappofbooks.ui.screens.ViewBook

@Composable
fun AppNavigation(themeViewModel: ThemeViewModel) {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

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
                    context = navController.context,
                    onBookClick = { navController.navigate(Screen.ViewBook.route) }
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
                ShelvesScreen(
                    onShelfClick = { shelf ->
                        navController.navigate(Screen.ShelfDetails.createRoute(shelf.id))
                    },
                    onAddShelfClick = {
                        navController.navigate(Screen.AddShelf.route)
                    }
                )
            }

            composable(
                route = Screen.ShelfDetails.route,
                arguments = listOf(navArgument("shelfId") { type = NavType.StringType })
            ) { backStackEntry ->
                val shelfId = backStackEntry.arguments?.getString("shelfId")
                ShelfDetailsScreen(
                    shelfId = shelfId,
                    onBackClick = { navController.popBackStack() }
                )
            }

            composable(Screen.AddShelf.route) {
                AddShelfScreen(
                    onBackClick = { navController.popBackStack() }
                )
            }

            composable(Screen.ViewBook.route) {
                ViewBook(
                    title = "Peter Pan in Wonderland",
                    author = "Samira Sales",
                    rate = 3.7,
                    sinopse = "Sinopse de teste do livro...",
                    imageUrl = "https://br.pinterest.com/pin/19492210989423958/",
                    publishYear = "2025",
                    publisher = "Bila-Bilu",
                    pageCount = "240",
                    status = "ABANDONADO",
                    shelf = "Romances",
                    onBackClick = { navController.popBackStack() },
                    onRegisterClick = { navController.navigate(Screen.RegisterReading.route) },
                )
            }

            composable(Screen.RegisterReading.route) {
                RegisterReadingScreen(
                    onNavigate = { navController.popBackStack() }
                )
            }
        }
    }
}