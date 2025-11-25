package com.example.livoappofbooks.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Library : Screen("library", "Biblioteca", Icons.Default.Home)
    object Register : Screen("register", "Registrar", Icons.Default.Add)
}
