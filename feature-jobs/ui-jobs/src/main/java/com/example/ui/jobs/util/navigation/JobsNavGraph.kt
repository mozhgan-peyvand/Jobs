package com.example.ui.jobs.util.navigation
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.base.routers.AppRouters
import com.example.base.util.ObserveLifecycle
import com.example.ui.jobs.screens.JobScreen
import com.example.ui.jobs.screens.JobViewModel


fun NavGraphBuilder.addJobsGraph(
    navHostController: NavHostController
    ) {
    navigation(
        startDestination = AppRouters.JobScreen.routers,
        route = AppRouters.JobGraph.routers
    ) {
         composable(AppRouters.JobScreen.routers) {

             val viewModel = hiltViewModel<JobViewModel>(it)
             viewModel.ObserveLifecycle(lifecycle = LocalLifecycleOwner.current.lifecycle)

             JobScreen(viewModel = viewModel)
        }
    }
}