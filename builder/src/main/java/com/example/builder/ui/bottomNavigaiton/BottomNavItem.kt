package com.example.builder.ui.bottomNavigaiton

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.base.R
import com.example.common.ui.view.theme.AppColors
import com.example.base.util.shape.LightSource
import com.example.base.util.shape.Pressed
import com.example.base.util.shape.RoundedCorner
import com.example.base.util.shape.neu

@Composable
fun AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

    val background =
        if (selected) MaterialTheme.colors.secondary else Color.Transparent
    val iconAndTextColor =
        if (selected) MaterialTheme.colors.onSecondary else MaterialTheme.colors.secondary

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(background)
            .clickable(onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            })
            .neu(
                lightShadowColor = AppColors.lightShadow(),
                darkShadowColor = AppColors.darkShadow(),
                lightSource = LightSource.LEFT_TOP,
                shape = Pressed(RoundedCorner(dimensionResource(id = R.dimen.spacing_6x))),
            )
            .padding(dimensionResource(id = R.dimen.spacing_half_base)),
    ) {
        Row(
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.spacing_3x),
                    end = dimensionResource(id = R.dimen.spacing_3x),
                    top = dimensionResource(id = R.dimen.spacing_2x),
                    bottom = dimensionResource(id = R.dimen.spacing_2x)
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_base))
        ) {
            Icon(
                painter = painterResource(id = if (selected) screen.icon_focused else screen.icon),
                contentDescription = "icon",
                tint = iconAndTextColor
            )
            AnimatedVisibility(visible = selected) {
                Text(
                    text = screen.title,
                    color = iconAndTextColor,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}