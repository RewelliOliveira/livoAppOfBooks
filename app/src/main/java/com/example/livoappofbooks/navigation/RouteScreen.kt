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

    object Shelfs : Screen(
        "shelfs",
        "Prateleiras",
        ScreenIcon.Vector(BookmarkOutlined)
    )

    object Search : Screen(
        "search",
        "Procurar",
        ScreenIcon.Vector(MagnifyingGlass)
    )

    object ViewBook : Screen(
        "view_book/{bookId}",
        "Visualizar Livro",
        null
    ) {
        fun createRoute(bookId: String) = "view_book/$bookId"
    }

    object ViewBookInit : Screen(
        "view_book_init/{bookId}",
        "Visualizar Livro Inicial",
        null
    ) {
        fun createRoute(bookId: String) = "view_book_init/$bookId"
    }

    object RegisterReading : Screen(
        "register_reading",
        "Registrar Leitura",
        null
    )

    object ShelfDetails : Screen(
        "shelf_details/{shelfId}",
        "Detalhes da Prateleira",
        null
    ) {
        fun createRoute(shelfId: String) = "shelf_details/$shelfId"
    }

    object EditShelf : Screen(
        "edit_shelf/{shelfId}",
        "Editar Prateleira",
        null
    ) {
        fun createRoute(shelfId: String) = "edit_shelf/$shelfId"
    }

    object AddShelf : Screen(
        "add_shelf",
        "Criar Prateleira",
        null
    )
}

sealed class ScreenIcon {
    data class Vector(val icon: ImageVector) : ScreenIcon()
    data class Drawable(val resId: Int) : ScreenIcon()
}
