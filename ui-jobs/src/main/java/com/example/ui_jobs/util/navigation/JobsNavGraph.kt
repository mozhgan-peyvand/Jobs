package com.example.ui_jobs.util.navigation
import androidx.compose.animation.*
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import androidx.navigation.navigation
import com.example.base.routers.AppRouters
import com.example.ui_jobs.screens.JobDetailScreen
import com.example.ui_jobs.screens.JobScreen


val springSpec = spring<IntOffset>(dampingRatio = Spring.DampingRatioMediumBouncy)
val tweenSpec = tween<IntOffset>(durationMillis = 2000, easing = CubicBezierEasing(0.08f,0.93f,0.68f,1.27f))

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addJobsGraph(
    navHostController: NavHostController
) {
    navigation(
        startDestination = AppRouters.JobScreen.routers,
        route = AppRouters.JobGraph.routers
    ) {
        composable(AppRouters.JobScreen.routers,   enterTransition = {
            slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = springSpec)
        },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = springSpec)
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = springSpec)
            },
            popExitTransition = {
                slideOutHorizontally(targetOffsetX = {1000 }, animationSpec = springSpec)
            }) {
            JobScreen { navHostController.navigate(JobRouters.JobDetailScreen.routers) }
        }

        composable(route = JobRouters.JobDetailScreen.routers,  enterTransition = {
            slideInVertically(initialOffsetY = { 1000 }, animationSpec = tweenSpec)
        },
            exitTransition = {
                slideOutVertically(targetOffsetY = { -5000 }, animationSpec = tweenSpec)
            },
            popEnterTransition = {
                slideInVertically(initialOffsetY = { -1000 }, animationSpec = tweenSpec)
            },
            popExitTransition = {
                slideOutVertically(targetOffsetY = { 4000 }, animationSpec = tweenSpec)
            }) {
            JobDetailScreen()
        }
    }
}