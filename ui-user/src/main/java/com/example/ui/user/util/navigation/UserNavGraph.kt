package com.example.ui.user.util.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.base.routers.AppRouters
import com.example.ui.user.screens.UserScreen


fun NavGraphBuilder.addUserNavGraph(
    navHostController: NavHostController,
) {
    navigation(
        route = AppRouters.UserGraph.routers,
        startDestination = AppRouters.UserScreen.routers
    ) {
        composable(AppRouters.UserScreen.routers) {
            UserScreen()
        }
    }
}