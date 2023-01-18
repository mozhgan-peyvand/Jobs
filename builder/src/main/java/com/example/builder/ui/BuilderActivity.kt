package com.example.builder.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.base.routers.AppRouters
import com.example.builder.ui.bottomNavigaiton.BottomBar
import com.example.common.ui.view.theme.AppTheme
import com.example.ui.jobs.util.ui.ImageButton
import com.example.ui.jobs.R
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
            val appTheme = remember {
                mutableStateOf(true)
            }
            AppTheme(darkTheme = appTheme.value) {
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
                    topBar = {
                        TitleWithThemeToggle(isDarkTheme = appTheme) {
                            appTheme.value = !appTheme.value
                        }
                    }

                ) { paddingValue ->

                    AnimatedNavHost(
                        navController = navController,
                        startDestination = AppRouters.JobGraph.routers,
                        modifier = Modifier.padding(paddingValue)
                    ) {
                        addJobsGraph(navController)
                        addUserNavGraph(navController)
                    }
                }
            }
        }
    }
}

@Composable
fun TitleWithThemeToggle(
    isDarkTheme: MutableState<Boolean>,
    onThemeToggle: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "MozhganPeivandian",
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.primary
        )
        ImageButton(
            modifier = Modifier.padding(8.dp),
            drawableResId = if (isDarkTheme.value) R.drawable.ic_light_mode
            else R.drawable.ic_dark_mode,
            contentDescription = "Toggle theme",
            onClick = onThemeToggle
        )
    }
}