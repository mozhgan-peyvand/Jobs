package com.example.ui_jobs.util.navigation
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import androidx.navigation.navigation
import com.example.base.routers.AppRouters
import com.example.ui_jobs.screens.JobDetailScreen
import com.example.ui_jobs.screens.JobScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addJobsGraph(
    navHostController: NavHostController
) {
    navigation(
        startDestination = AppRouters.JobScreen.routers,
        route = AppRouters.JobGraph.routers
    ) {
        composable(AppRouters.JobScreen.routers, enterTransition = {
            when (initialState.destination.route) {
                JobRouters.JobDetailScreen.routers -> slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(1000)
                ) // slide in the profile screen
                else -> null // use the defaults
            }
        }) {
            JobScreen { navHostController.navigate(JobRouters.JobDetailScreen.routers) }
        }

        composable(route = JobRouters.JobDetailScreen.routers, exitTransition = {
            when (targetState.destination.route) {
                AppRouters.JobScreen.routers -> slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(1000)
                ) // slowly fade it out
                else -> null // use the defaults
            }
        }) {
            JobDetailScreen()
        }
    }
}