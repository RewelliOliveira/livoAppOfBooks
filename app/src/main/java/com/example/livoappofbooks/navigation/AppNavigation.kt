package com.example.livoappofbooks.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.livoappofbooks.ui.viewModel.ThemeViewModel
import com.example.livoappofbooks.ui.screens.LibraryScreen
import com.example.livoappofbooks.ui.screens.Prateleira
import com.example.livoappofbooks.ui.screens.ProfileScreen
import com.example.livoappofbooks.ui.screens.RegisterReadingScreen
import com.example.livoappofbooks.ui.screens.SearchScreen
import com.example.livoappofbooks.ui.screens.ShelfsScreen // Adicione este import
import com.example.livoappofbooks.ui.screens.ViewBook

@Composable
fun AppNavigation(themeViewModel: ThemeViewModel) {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            // Adicione Screen.Shelfs.route à lista de telas que mostram a BottomBar
            if (currentRoute in listOf(
                    Screen.Library.route,
                    Screen.Search.route,
                    Screen.Profile.route,
                    Screen.Shelfs.route // Adicione aqui
                )) {
                BottomBar(navController)
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Screen.Library.route, // Ou Screen.Shelfs.route se quiser começar aqui
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(Screen.Library.route) {
                LibraryScreen(
                    onNavigate = { navController.navigate(Screen.Profile.route) },
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
                ShelfsScreen(
                    navController = navController,
                    onShelfClick = {
                        TODO("TELA DA PRATELEIRA")
                    },
                    onAddShelfClick = {
                        TODO("TELA DE ADICIONAR PRATELEIRA" )
                    }
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