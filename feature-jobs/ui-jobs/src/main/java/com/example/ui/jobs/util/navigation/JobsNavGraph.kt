package com.example.ui.jobs.util.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.base.routers.AppRouters
import com.example.base.util.ObserveLifecycle
import com.example.ui.jobs.screens.*


@RequiresApi(Build.VERSION_CODES.M)
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

            JobScreen(
                viewModel = viewModel,
                navHostController
            )
        }

        composable(
            JobRouters.JobDetailScreen.routers + "/{$KEY_ARTICLE_ID}",
            arguments = listOf(navArgument(KEY_ARTICLE_ID) { type = NavType.StringType })
        ) {
            val viewModel = hiltViewModel<JobDetailViewModel>()
            viewModel.ObserveLifecycle(lifecycle = LocalLifecycleOwner.current.lifecycle)
            JobDetailScreen(viewModel = viewModel)
        }
    }
}