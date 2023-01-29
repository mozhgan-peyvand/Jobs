package com.example.builder.ui.bottomNavigaiton

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.base.routers.AppRouters
import com.example.builder.R as BuilderR
import com.example.base.R as BaseR

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int,
    val icon_focused: Int
) {

    // for home
    object Home : BottomBarScreen(
        route = AppRouters.JobScreen.routers,
        title = "Home",
        icon = BuilderR.drawable.ic_bottom_home,
        icon_focused = BuilderR.drawable.ic_bottom_home_focused
    )

    // for profile
    object Profile : BottomBarScreen(
        route = AppRouters.UserScreen.routers,
        title = "Profile",
        icon = BuilderR.drawable.ic_bottom_profile,
        icon_focused = BuilderR.drawable.ic_bottom_profile_focused
    )

}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Profile
    )

    val navStackBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navStackBackEntry?.destination

    Row(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxWidth()
            .padding(dimensionResource(id = BaseR.dimen.spacing_2x)),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }

}