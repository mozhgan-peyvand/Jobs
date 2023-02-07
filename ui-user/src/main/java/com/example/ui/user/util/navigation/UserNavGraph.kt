package com.example.ui.user.util.navigation

import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.base.routers.AppRouters
import com.example.base.util.ObserveLifecycle
import com.example.ui.user.screens.UserScreen
import com.example.ui.user.screens.UserViewModel


fun NavGraphBuilder.addUserNavGraph(
    navHostController: NavHostController,
) {
    navigation(
        route = AppRouters.UserGraph.routers,
        startDestination = AppRouters.UserScreen.routers
    ) {
        composable(AppRouters.UserScreen.routers) {
            val viewModel = hiltViewModel<UserViewModel>()
            viewModel.ObserveLifecycle(lifecycle = LocalLifecycleOwner.current.lifecycle)
            UserScreen(viewModel)
        }
    }
}