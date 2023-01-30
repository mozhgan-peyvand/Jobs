package com.example.builder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.base.routers.AppRouters
import com.example.builder.ui.bottomNavigaiton.BottomBar
import com.example.builder.ui.splash.addSplash
import com.example.common.ui.view.theme.AppTheme
import com.example.common.ui.view.theme.h3Primary
import com.example.ui.jobs.util.navigation.addJobsGraph
import com.example.ui.jobs.util.ui.ImageButton
import com.example.ui.user.util.navigation.addUserNavGraph
import dagger.hilt.android.AndroidEntryPoint
import com.example.base.R as BaseR
import com.example.builder.R as BuilderR

@AndroidEntryPoint
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
                mutableStateOf(false)
            }
            val showBottomBarAndTopBar = remember { mutableStateOf(false) }

            AppTheme(darkTheme = appTheme.value) {
                navController = rememberNavController()
                navController.addOnDestinationChangedListener { _, destination, _ ->
                    showBottomBarAndTopBar.value = when (destination.route) {
                        AppRouters.SplashScreen.routers -> false
                        else -> true
                    }
                }

                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    scaffoldState = scaffoldState,
                    bottomBar = {
                        if (showBottomBarAndTopBar.value) {
                            BottomBar(navController = navController)
                        }
                    },
                    topBar = {
                        if (showBottomBarAndTopBar.value) {
                            TitleWithThemeToggle(isDarkTheme = appTheme) {
                                appTheme.value = !appTheme.value
                            }
                        }
                    }

                ) { paddingValue ->

                    NavHost(
                        navController = navController,
                        startDestination = AppRouters.AppGraph.routers,
                        modifier = Modifier.padding(paddingValue)
                    ) {
                        addSplash {
                            navController.navigate(AppRouters.JobScreen.routers){
                                popUpTo(AppRouters.SplashScreen.routers){
                                    inclusive = true
                                }
                            }
                        }
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
            modifier = Modifier.padding(horizontal = dimensionResource(id = BaseR.dimen.spacing_4x)),
            text = stringResource(id = BuilderR.string.label_title_toolbar),
            style = MaterialTheme.typography.h3Primary()
        )
        ImageButton(
            modifier = Modifier.padding(dimensionResource(id = BaseR.dimen.spacing_2x)),
            drawableResId = if (isDarkTheme.value) BuilderR.drawable.ic_light_mode
            else BuilderR.drawable.ic_dark_mode,
            contentDescription = "Toggle theme",
            onClick = onThemeToggle
        )
    }
}