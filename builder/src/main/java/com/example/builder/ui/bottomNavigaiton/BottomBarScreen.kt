package com.example.builder.ui.bottomNavigaiton

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.base.routers.AppRouters
import com.example.base.shape.Punched
import com.example.base.shape.neumorphic
import com.example.builder.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int,
    val icon_focused: Int
) {
    // for favorite
    object Favorite : BottomBarScreen(
        route = AppRouters.FavoriteScreen.routers,
        title = "Favorite",
        icon = R.drawable.ic_bottom_favorite,
        icon_focused = R.drawable.ic_bottom_favorite_focused
    )

    // for home
    object Home : BottomBarScreen(
        route = AppRouters.JobScreen.routers,
        title = "Home",
        icon = R.drawable.ic_bottom_home,
        icon_focused = R.drawable.ic_bottom_home_focused
    )

    // for profile
    object Profile : BottomBarScreen(
        route = AppRouters.UserScreen.routers,
        title = "Profile",
        icon = R.drawable.ic_bottom_profile,
        icon_focused = R.drawable.ic_bottom_profile_focused
    )

}
@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Favorite,
        BottomBarScreen.Home,
        BottomBarScreen.Profile
    )

    val navStackBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navStackBackEntry?.destination

    Row(
        modifier = Modifier
            .background(Color(236, 234, 235))
            .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
            .fillMaxWidth()
            ,
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