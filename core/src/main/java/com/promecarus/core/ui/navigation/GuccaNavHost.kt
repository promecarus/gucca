package com.promecarus.core.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.promecarus.core.ui.route.AboutRoute
import com.promecarus.core.ui.route.FavoriteRoute
import com.promecarus.core.ui.route.HomeRoute

@Composable
fun GuccaNavHost(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable(route = Destination.About.route) {
            AboutRoute()
        }

        composable(route = Destination.Favorite.route) {
            FavoriteRoute()
        }

        composable(route = Destination.Home.route) {
            HomeRoute()
        }
    }
}
