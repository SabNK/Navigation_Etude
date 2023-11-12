package com.example.navigationetude

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.example.navigationetude.destinations.AppScreenDestination
import com.example.navigationetude.destinations.Screen1Destination
import com.example.navigationetude.destinations.Screen2Destination
import com.example.navigationetude.destinations.Screen3Destination
import com.example.navigationetude.destinations.SettingsScreenDestination
import com.example.navigationetude.ui.theme.NavigationEtudeTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.Direction

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationEtudeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}
//https://medium.com/@chiragthummar16/jetpack-compose-bottom-navigation-with-scaffold-material3-717e28ccc811
@Composable
fun MainScreen() {
    DestinationsNavHost(navGraph = NavGraphs.root)
}

@RootNavGraph(start = true)
@Destination
@Composable
fun WelcomeScreen(navigator: DestinationsNavigator) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Magenta),
        contentAlignment = Alignment.Center) {
        Column (verticalArrangement = Arrangement.spacedBy(30.dp)) {
            Text(text = "WelcomeScreen")
            Button(onClick = {navigator.navigate(AppScreenDestination)}) {
                Icon(Icons.Default.ArrowForward, "toApp")
            }
        }
    }
}

@SuppressLint("RestrictedApi")
@Destination
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen(
    navigator: DestinationsNavigator
) {
    val showBottomBar = remember { mutableStateOf(true) }
    val title = remember {
        mutableStateOf("Home")
    }
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(
                    text = title.value,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )
            }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            ), actions = {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    contentDescription = "Settings",
                    modifier = Modifier
                        .clickable {
                            navigator.navigate(SettingsScreenDestination)
                        }
                        .padding(8.dp)
                )
            })
        },
        bottomBar = {
            if (showBottomBar.value) {
                BottomNavBar(
                    currentDestination = navController.appCurrentDestinationAsState().value
                        ?: NavGraphs.bottom.startAppDestination
                ) {
                    title.value = when (it) {
                        Screen1Destination -> "Home"
                        Screen2Destination -> "Account"
                        Screen3Destination -> "Favorite"
                        else -> {
                            "Unknown screen"
                        }
                    }
                    navController.navigate(it) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            DestinationsNavHost(
                navGraph = NavGraphs.bottom,
                navController = navController)
        }
    }
}


@Composable
private fun BottomNavBar(
    currentDestination: com.example.navigationetude.destinations.Destination?,
    onNavigateToDestination: (Direction) -> Unit
) {
    NavigationBar(
        modifier = Modifier
            .windowInsetsPadding(
                WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom)
            )
            .height(70.dp),
    ) {
        BottomBarDestination.values().forEach { destination ->
            val selected = currentDestination == destination.direction
            NavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination.direction) },
                icon = {
                    val icon = if (selected) {
                        destination.selectedIcon
                    } else {
                        destination.unselectedIcon
                    }
                    Icon(
                        imageVector = icon,
                        modifier = Modifier.size(16.dp),
                        contentDescription = destination.iconText
                    )
                },
                label = {
                    Text(
                        text = destination.iconText
                    )
                }
            )
        }
    }
}