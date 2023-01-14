package com.example.builder.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.base.routers.AppRouters
import com.example.builder.ui.bottomNavigaiton.BottomBar
import com.example.ui_favorite.util.navigation.addFavoriteGraph
import com.example.ui_jobs.util.navigation.addJobsGraph
import com.example.ui_user.util.navigation.addUserNavGraph
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.launch

class BuilderActivity : ComponentActivity() {

    companion object {
        var appId = ""
    }

    private lateinit var navController: NavHostController

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var filterResultList = remember {
                mutableStateListOf<String>()
            }
            navController = rememberAnimatedNavController()
            // create a scaffold state, set it to close by default
            val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
            // Create a coroutine scope. Opening of Drawer
            // and snackbar should happen in background
            // thread without blocking main thread
            val coroutineScope = rememberCoroutineScope()
            Scaffold(
                // pass the scaffold state
                scaffoldState = scaffoldState,
                // pass the topbar we created
                topBar = {
                    TopBar(
                        // When menu is clicked open the
                        // drawer in coroutine scope
                        onMenuClicked = {
                            coroutineScope.launch {
                                // to close use -> scaffoldState.drawerState.close()
                                scaffoldState.drawerState.open()
                            }
                        },
                        filterResultList,
                        { filterResultList.remove(it) }
                    )
                },
                bottomBar = { BottomBar(navController = navController) },
                drawerContent = {
                    BottomSheetLayout(
                        {
                            coroutineScope.launch {
                                // to close use -> scaffoldState.drawerState.close()
                                scaffoldState.drawerState.close()
                            }
                        },
                        { filterResultList.addAll(it) },
                        { filterResultList.clear() }
                    )
                }
            ) { paddingValue ->

                AnimatedNavHost(
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