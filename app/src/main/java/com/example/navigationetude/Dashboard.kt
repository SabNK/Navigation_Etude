package com.example.navigationetude

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun Dashboard(mainContent: @Composable (ColumnScope.() -> Unit)? = null) {
    LocalNavigationState.baseNavigation.printBackStack()
    Column {
        Column(Modifier.weight(1f)) {
            mainContent?.invoke(this)
        }
        Row (Modifier.fillMaxWidth().height(40.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
            SingleTab(
                text = "Tab 1 Inc False",
                isSelected = LocalNavigationState.baseNavigation.isRouteActive(Route.Base_tab_1.link),
                onClick = {
                    LocalNavigationState.baseNavigation.navigateTo(Route.Base_tab_1.link, popUpTo = true, popUpToInclusive = false)
                }
            )
            SingleTab(
                text = "Tab 2 Inc False",
                isSelected = LocalNavigationState.baseNavigation.isRouteActive(Route.Base_tab_2.link),
                onClick = {
                    LocalNavigationState.baseNavigation.navigateTo(Route.Base_tab_2.link, popUpTo = true, popUpToInclusive = false)
                }
            )
            SingleTab(
                text = "Tab 3 Inc False",
                isSelected = LocalNavigationState.baseNavigation.isRouteActive(Route.Base_tab_3.link),
                onClick = {
                    LocalNavigationState.baseNavigation.navigateTo(Route.Base_tab_3.link, popUpTo = true, popUpToInclusive = false)
                }
            )
        }
        Row (Modifier.fillMaxWidth().height(40.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
            SingleTab(
                text = "Tab 1 Inc True",
                isSelected = LocalNavigationState.baseNavigation.isRouteActive(Route.Base_tab_1.link),
                onClick = {
                    LocalNavigationState.baseNavigation.navigateTo(Route.Base_tab_1.link, popUpTo = true, popUpToInclusive = true)
                }
            )
            SingleTab(
                text = "Tab 2 Inc True",
                isSelected = LocalNavigationState.baseNavigation.isRouteActive(Route.Base_tab_2.link),
                onClick = {
                    LocalNavigationState.baseNavigation.navigateTo(Route.Base_tab_2.link, popUpTo = true, popUpToInclusive = true)
                }
            )
            SingleTab(
                text = "Tab 3 Inc True",
                isSelected = LocalNavigationState.baseNavigation.isRouteActive(Route.Base_tab_3.link),
                onClick = {
                    LocalNavigationState.baseNavigation.navigateTo(Route.Base_tab_3.link, popUpTo = true, popUpToInclusive = true)
                }
            )
        }
        Row (Modifier.fillMaxWidth().height(40.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
            SingleTab(
                text = "Tab 1 SingleTop",
                isSelected = LocalNavigationState.baseNavigation.isRouteActive(Route.Base_tab_1.link),
                onClick = {
                    LocalNavigationState.baseNavigation.getNavController.navigate(Route.Base_tab_1.link) {
//                        LocalNavigationState.baseNavigation.getNavController.graph.startDestinationRoute?.let { route ->
//                            popUpTo(route) {
//                                saveState = true
//                            }
//                        }

                        launchSingleTop = true
//                        restoreState = true
                    }
                }
            )
            SingleTab(
                text = "Tab 2 SingleTop",
                isSelected = LocalNavigationState.baseNavigation.isRouteActive(Route.Base_tab_2.link),
                onClick = {
                    LocalNavigationState.baseNavigation.getNavController.navigate(Route.Base_tab_2.link) {
//                        LocalNavigationState.baseNavigation.getNavController.graph.startDestinationRoute?.let { route ->
//                            popUpTo(route) {
//                                saveState = true
//                            }
//                        }
                        launchSingleTop = true
//                        restoreState = true
                    }
                }
            )
            SingleTab(
                text = "Tab 3 SingleTop",
                isSelected = LocalNavigationState.baseNavigation.isRouteActive(Route.Base_tab_3.link),
                onClick = {
                    val animalsTab = AnimalsParameters(AnimalsTab.BIRD)
                    val tab3Link = NavRouteName.tab_3+"/"+ AnimalsParametersType.serializeAsValue(animalsTab)

                    LocalNavigationState.baseNavigation.getNavController.navigate(tab3Link) {
//                        LocalNavigationState.baseNavigation.getNavController.graph.startDestinationRoute?.let { route ->
//                            popUpTo(route) {
//                               saveState = true
//                            }
//                        }
                        launchSingleTop = true
//                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
private fun SingleTab(text: String =  "", isSelected: Boolean = false, onClick: () -> Unit) {
    Box(modifier = Modifier
        .fillMaxHeight()
        .background(color = if (isSelected) Color.Gray else Color.White)
        .clickable {
            onClick()
        }) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = text
        )
    }
}