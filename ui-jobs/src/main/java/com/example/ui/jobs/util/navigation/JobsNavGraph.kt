package com.example.ui.jobs.util.navigation
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.base.routers.AppRouters
import com.example.ui.jobs.screens.JobScreen


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addJobsGraph(
    navHostController: NavHostController
    ) {
    navigation(
        startDestination = AppRouters.JobScreen.routers,
        route = AppRouters.JobGraph.routers
    ) {
        composable(AppRouters.JobScreen.routers) {
            JobScreen()
        }
    }
}