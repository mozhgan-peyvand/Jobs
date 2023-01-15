package com.example.builder.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.base.routers.AppRouters
import com.example.builder.ui.bottomNavigaiton.BottomBar
import com.example.ui.jobs.util.navigation.addJobsGraph
import com.example.ui.user.util.navigation.addUserNavGraph
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

class BuilderActivity : ComponentActivity() {

    companion object {
        var appId = ""
    }

    private lateinit var navController: NavHostController

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var selectedScreen = remember {
                mutableStateOf(1)
            }

            navController = rememberAnimatedNavController()
            // create a scaffold state, set it to close by default
            val scaffoldState = rememberScaffoldState()
            // Create a coroutine scope. Opening of Drawer
            // and snackbar should happen in background
            // thread without blocking main thread
            Scaffold(
                // pass the scaffold state
                scaffoldState = scaffoldState,
                // pass the topbar we created
                bottomBar = { BottomBar(navController = navController) },

            ) { paddingValue ->

                AnimatedNavHost(
                    navController = navController,
                    startDestination = AppRouters.JobGraph.routers,
                    modifier = Modifier.padding(paddingValue)
                ) {
                    addJobsGraph(navController) { selectedScreen.value = 1 }
                    addUserNavGraph(navController){selectedScreen.value = 2}
                }
            }
        }
    }
}