package com.example.builder.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.base.routers.AppRouters
import com.example.builder.ui.bottomNavigaiton.AddItem
import com.example.builder.ui.bottomNavigaiton.BottomBar
import com.example.builder.ui.bottomNavigaiton.BottomBarScreen
import com.example.ui_favorite.util.navigation.addFavoriteGraph
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

            Scaffold(
                bottomBar = { BottomBar(navController = navController) }
            ) { paddingValue ->

                NavHost(
                    navController = navController,
                    startDestination = AppRouters.JobGraph.routers,
                    modifier = Modifier.padding(paddingValue)
                ) {
                    addJobsGraph(navController)
                    addUserNavGraph(navController)
                    addFavoriteGraph(navController)
                }
            }
        }
    }
}