package com.example.ui.jobs.util.navigation
import androidx.compose.animation.*
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.example.base.routers.AppRouters
import com.example.ui.jobs.screens.JobScreen
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addJobsGraph(
    navHostController: NavHostController,
    function: () -> Unit
) {
    navigation(
        startDestination = AppRouters.JobScreen.routers,
        route = AppRouters.JobGraph.routers
    ) {
        composable(AppRouters.JobScreen.routers) {
            JobScreen(function)
        }
    }
}