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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.livoappofbooks.data.repository.LibraryRepository
import com.example.livoappofbooks.data.remote.RetrofitInstance
import com.example.livoappofbooks.data.service.LibraryService
import com.example.livoappofbooks.ui.viewModel.ThemeViewModel
import com.example.livoappofbooks.ui.screens.*
import com.example.livoappofbooks.ui.viewModel.*

@Composable
fun AppNavigation(themeViewModel: ThemeViewModel) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val context = LocalContext.current

    val shelvesViewModel: ShelvesViewModel = viewModel(
        factory = ShelvesViewModelFactory(context)
    )

    // Configuração do Repositório e Serviço
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
                    context = navController.context,
                    onBookClick = { book ->
                            // Navegação usando o padrão da developer (createRoute)
                            navController.navigate(Screen.ViewBook.createRoute(book.id))
                        }
                )
            }

            composable(Screen.Search.route) {
                SearchScreen(
                    onBookClick = { bookId, isInLibrary ->
                        if (isInLibrary) {
                            navController.navigate(Screen.ViewBook.createRoute(bookId))
                        } else {
                            navController.navigate(Screen.ViewBookInit.createRoute(bookId))
                        }
                    }
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
                    onShelfClick = { shelf -> navController.navigate(Screen.ShelfDetails.createRoute(shelf.id)) },
                    onAddShelfClick = { navController.navigate(Screen.AddShelf.route) },
                    viewModel = shelvesViewModel
                )
            }

            composable(
                route = Screen.ShelfDetails.route,
                arguments = listOf(navArgument("shelfId") { type = NavType.StringType })
            ) { backStackEntry ->
                val shelfId = backStackEntry.arguments?.getString("shelfId")
                ShelfDetailsScreen(
                    shelfId = shelfId,
                    viewModel = shelvesViewModel,
                    onBackClick = { navController.popBackStack() },
                    onEditClick = { id -> navController.navigate(Screen.EditShelf.createRoute(id)) },
                    onBookClick = { bookId -> navController.navigate(Screen.ViewBook.createRoute(bookId.toString())) }
                )
            }

            composable(
                route = Screen.EditShelf.route,
                arguments = listOf(navArgument("shelfId") { type = NavType.StringType })
            ) {
                 EditShelfScreen(
                    viewModel = shelvesViewModel,
                    onBackClick = { navController.popBackStack() },
                    onDeleteSuccess = {
                        navController.popBackStack(Screen.Shelfs.route, inclusive = false)
                    }
                )
            }

            composable(Screen.AddShelf.route) {
                AddShelfScreen(
                    viewModel = shelvesViewModel,
                    onBackClick = { navController.popBackStack() }
                )
            }

            // Rota Dinâmica que aceita o ID do livro
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