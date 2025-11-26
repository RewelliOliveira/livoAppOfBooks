package com.example.livoappofbooks.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.livoappofbooks.ui.icons.BookOpen
import com.example.livoappofbooks.ui.icons.User

sealed class Screen(
    val route: String,
    val label: String,
    val icon: ScreenIcon
) {
    object Library : Screen(
        "library",
        "Biblioteca",
        ScreenIcon.Vector(BookOpen)
    )

    object Profile : Screen(
        "profile",
        "Perfil",
        ScreenIcon.Vector(User)
    )
}

sealed class ScreenIcon {
    data class Vector(val icon: ImageVector) : ScreenIcon()
    data class Drawable(val resId: Int) : ScreenIcon()
}
