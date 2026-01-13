package com.example.livoappofbooks.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.livoappofbooks.ui.theme.*

@Composable
fun BottomBar(navController: NavController) {

    val screens = listOf(
        Screen.Library,
        Screen.Search,
        Screen.Shelfs,
        Screen.Profile
    )

    NavigationBar(
        containerColor = background
    ) {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination?.route

        screens.forEach { screen ->

            NavigationBarItem(
                selected = currentDestination == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                    }
                },
                icon = {
                    when (val icon = screen.icon) {
                        is ScreenIcon.Vector -> {
                            Icon(
                                imageVector = icon.icon,
                                contentDescription = screen.label,
                                modifier = Modifier.size(25.dp)
                            )
                        }

                        is ScreenIcon.Drawable -> {
                            Icon(
                                painter = painterResource(id = icon.resId),
                                contentDescription = screen.label,
                                modifier = Modifier.size(25.dp)
                            )
                        }

                        else -> {}
                    }
                },
                label = { Text(text = screen.label) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = outline,
                    unselectedIconColor = tertiary,
                    selectedTextColor = outline,
                    unselectedTextColor = tertiary,
                    indicatorColor = onBackground.copy(alpha = 0.1f)
                )
            )
        }
    }
}