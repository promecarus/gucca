package com.promecarus.gucca

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.promecarus.core.ui.navigation.Destination
import com.promecarus.core.ui.navigation.GuccaNavHost

@Composable
fun GuccaApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    currentRoute: String = navController.currentBackStackEntryAsState().value?.destination?.route
        ?: Destination.Home.route,
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar {
                listOf(
                    Destination.Home,
                    Destination.Favorite,
                    Destination.About,
                ).forEach { destination ->
                    NavigationBarItem(
                        selected = destination.route == currentRoute,
                        onClick = {
                            navController.navigate(destination.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                restoreState = true
                                launchSingleTop = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (destination.route == currentRoute) destination.selectedIcon else destination.unselectedIcon,
                                contentDescription = destination.route,
                            )
                        },
                        label = {
                            Text(text = destination.route)
                        },
                    )
                }
            }
        },
    ) { paddingValues ->
        GuccaNavHost(
            navController = navController,
            startDestination = Destination.Home.route,
            modifier = Modifier.padding(paddingValues = paddingValues),
        )
    }
}
