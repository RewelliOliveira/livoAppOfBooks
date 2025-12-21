package com.example.livoappofbooks.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.livoappofbooks.ui.icons.BookOpen
import com.example.livoappofbooks.ui.icons.BookmarkOutlined
import com.example.livoappofbooks.ui.icons.MagnifyingGlass
import com.example.livoappofbooks.ui.icons.User

sealed class Screen(
    val route: String,
    val label: String,
    val icon: ScreenIcon?
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

    object Search : Screen(
        "search",
        "Procurar",
        ScreenIcon.Vector(MagnifyingGlass)
    )

    object Shelfs : Screen(
        "shelfs",
        "Prateleiras",
        ScreenIcon.Vector(BookmarkOutlined)
    )

    object ViewBook : Screen(
        "view_book",
        "Visualizar Livro",
        null
    )

    object RegisterReading : Screen(
        "register_reading",
        "Registrar Leitura",
        null
    )
}

sealed class ScreenIcon {
    data class Vector(val icon: ImageVector) : ScreenIcon()
    data class Drawable(val resId: Int) : ScreenIcon()
}