package com.example.builder.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.base.routers.AppRouters
import com.example.ui_jobs.util.navigation.addJobsGraph
import com.example.ui_user.util.navigation.addUserNavGraph
import com.example.builder.R
import com.example.builder.ui.bottomNavigaiton.CbnMenuItem
import com.example.builder.ui.bottomNavigaiton.CurvedBottomNavigationView

class BuilderActivity : ComponentActivity() {

    companion object {
        var appId = ""
    }

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                val activeIndex = savedInstanceState?.getInt("activeIndex") ?: 1
                val menuItems = arrayOf(
                    CbnMenuItem(
                        R.drawable.ic_dashboard,
                        R.drawable.avd_dashboard,
                        AppRouters.UserScreen.routers
                    ),
                    CbnMenuItem(
                        R.drawable.ic_home,
                        R.drawable.avd_home,
                        AppRouters.JobScreen.routers

                    ),
                    CbnMenuItem(
                        R.drawable.ic_profile,
                        R.drawable.avd_profile,
                        AppRouters.UserScreen.routers

                    )
                )
                navController = rememberNavController()

                val navBackStackEntry = navController.currentBackStackEntryAsState()

                Box {
                    // Adds view to Compose
                    AppNavigation()
                    AndroidView(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter), // Occupy the max size in the Compose UI tree
                        factory = { context ->
                            // Creates view
                            CurvedBottomNavigationView(context).apply {
                                // Sets up listeners for View -> Compose communication
                                setMenuItems(menuItems, activeIndex)
                        }
                            },
                        update = { view ->
                            // View's been inflated or state read in this block has been updated
                            // Add logic here if necessary

                            // As selectedItem is read here, AndroidView will recompose
                            // whenever the state changes
                            // Example of Compose -> View communication
                            view.setupWithNavController(navBackStackEntry,navController)
                        }
                    )
                }
            }
        }

    }

    @Composable
    fun AppNavigation() {
        NavHost(navController = navController, startDestination = AppRouters.JobGraph.routers) {
            addJobsGraph(navController)
            addUserNavGraph(navController)
        }
    }
}