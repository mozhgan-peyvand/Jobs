package com.example.ui_favorite.util.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.base.routers.AppRouters
import com.example.ui_favorite.FavoriteScreen

fun NavGraphBuilder.addFavoriteGraph(
    navController: NavController
) {
    navigation(
        route = AppRouters.FavoriteGraph.routers,
        startDestination = AppRouters.FavoriteScreen.routers
    ) {
        composable(route = AppRouters.FavoriteScreen.routers) {
            FavoriteScreen()
        }
    }
}