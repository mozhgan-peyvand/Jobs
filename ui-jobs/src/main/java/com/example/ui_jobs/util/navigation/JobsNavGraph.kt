package com.example.ui_jobs.util.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.base.routers.AppRouters
import com.example.ui_jobs.screens.JobScreen

fun NavGraphBuilder.addJobsGraph(
    navHostController: NavHostController
){
    navigation(startDestination = AppRouters.JobScreen.routers, route = AppRouters.JobGraph.routers) {
        composable(route = AppRouters.JobScreen.routers) {
            JobScreen()
        }
    }
}