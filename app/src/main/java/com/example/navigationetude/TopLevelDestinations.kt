package com.example.navigationetude

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.navigationetude.destinations.Screen1Destination
import com.example.navigationetude.destinations.Screen2Destination
import com.example.navigationetude.destinations.Screen3Destination
import com.ramcosta.composedestinations.annotation.NavGraph
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec


@NavGraph
annotation class BottomNavGraph(
    val start: Boolean = false
)

enum class BottomBarDestination(
    val direction: DirectionDestinationSpec,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconText: String,
) {
    Home(
        direction = Screen1Destination,
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        iconText = "Home"),
    Account(
        direction = Screen2Destination,
        selectedIcon = Icons.Filled.AccountBox,
        unselectedIcon = Icons.Outlined.AccountBox,
        iconText = "Account"),
    Favorite(
        direction = Screen3Destination,
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Outlined.FavoriteBorder,
        iconText = "Favorite"),
}
