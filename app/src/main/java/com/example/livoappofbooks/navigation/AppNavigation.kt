package com.example.livoappofbooks.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.livoappofbooks.data.remote.RetrofitInstance
import com.example.livoappofbooks.data.remote.local.TokenManager
import com.example.livoappofbooks.data.remote.shelves.ShelvesRepository
import com.example.livoappofbooks.data.repository.LibraryRepository
import com.example.livoappofbooks.data.service.LibraryService
import com.example.livoappofbooks.ui.screens.*
import com.example.livoappofbooks.ui.viewModel.ProfileViewModel
import com.example.livoappofbooks.ui.viewModel.ProfileViewModelFactory
import com.example.livoappofbooks.ui.viewModel.SearchViewModel
import com.example.livoappofbooks.ui.viewModel.SearchViewModelFactory
import com.example.livoappofbooks.ui.viewModel.ShelvesViewModel
import com.example.livoappofbooks.ui.viewModel.ShelvesViewModelFactory
import com.example.livoappofbooks.ui.viewModel.ThemeViewModel

@Composable
fun AppNavigation(themeViewModel: ThemeViewModel) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val context = LocalContext.current

    // Gerenciador de Token para o Logout
    val tokenManager = remember { TokenManager(context) }

    // ViewModel das Prateleiras
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

    // Repositório de Prateleiras para ViewBookScreen
    val shelvesRepository = remember {
        ShelvesRepository(context)
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
                        navController.navigate(Screen.ViewBook.createRoute(book.id))
                    }
                )
            }

            composable(Screen.Search.route) {
                val searchViewModel: SearchViewModel = viewModel(
                    factory = SearchViewModelFactory(libraryRepository)
                )
                SearchScreen(
                    viewModel = searchViewModel,
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
                // Cria o ViewModel usando a Factory
                val context = context
                val profileViewModel: ProfileViewModel = viewModel(
                    factory = ProfileViewModelFactory(libraryRepository, tokenManager, context)
                )

                ProfileScreen(
                    themeViewModel = themeViewModel,
                    viewModel = profileViewModel,
                    onLogoutSuccess = {
                        navController.navigate("login_screen") {
                            popUpTo(0) { inclusive = true }
                        }
                    }
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

            composable(
                route = Screen.ViewBook.route, arguments = listOf(navArgument("bookId") { type = NavType.StringType })
            ) { backStackEntry ->
                val bookId = backStackEntry.arguments?.getString("bookId")
                if (bookId != null) {
                    ViewBookScreen(
                        bookId = bookId,
                        repository = libraryRepository,
                        shelvesRepository = shelvesRepository,
                        onBackClick = { navController.popBackStack() },
                        onRegisterClick = { navController.navigate(Screen.RegisterReading.route) }
                    )
                }
            }

            composable(
                route = Screen.ViewBookInit.route,
                arguments = listOf(navArgument("bookId") { type = NavType.StringType })
            ) { backStackEntry ->
                val bookId = backStackEntry.arguments?.getString("bookId")
                if (bookId != null) {
                    ViewBookInitScreen(
                        bookId = bookId,
                        repository = libraryRepository,
                        onBackClick = { navController.popBackStack() },
                        onBookAdded = { id ->
                            navController.navigate(Screen.ViewBook.createRoute(id)) {
                                popUpTo(Screen.ViewBookInit.createRoute(id)) { inclusive = true }
                            }
                        }
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