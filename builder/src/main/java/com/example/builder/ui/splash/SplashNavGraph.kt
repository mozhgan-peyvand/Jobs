package com.example.builder.ui.splash

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.base.routers.AppRouters
import com.example.ui.jobs.screens.JobScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addSplash(
    navigateToJobScreen : () -> Unit
) {
    navigation(
        startDestination = AppRouters.SplashScreen.routers,
        route = AppRouters.AppGraph.routers
    ) {
        composable(AppRouters.SplashScreen.routers) {
            SplashScreen(navigateToJobScreen)
        }
    }
}