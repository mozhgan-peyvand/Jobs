package com.example.builder.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.base.routers.AppRouters
import com.example.ui_jobs.util.navigation.addJobsGraph
import com.example.ui_user.util.navigation.addUserNavGraph

class BuilderActivity : ComponentActivity() {

    companion object {
        var appId = ""
    }

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()
            appNavigation()
        }

    }

    @Composable
    fun appNavigation() {
        NavHost(navController = navController, startDestination = AppRouters.JobGraph.routers) {
            addJobsGraph(navController)
            addUserNavGraph(navController)
        }
    }
}