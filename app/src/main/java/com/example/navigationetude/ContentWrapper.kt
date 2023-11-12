package com.example.navigationetude

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlin.random.Random

@Composable
fun ContentWrapper() {
    LocalNavigationState = MultiNavigationStates(
        rootNavigation = rememberMultiNavigationAppState(startDestination = ROOT_ROUTE),

        baseNavigation = rememberMultiNavigationAppState(startDestination = Route.Base_tab_1.link),
        productNavigation = rememberMultiNavigationAppState(startDestination = Route.ProductList.link)
    )
    NavHost(
        navController = LocalNavigationState.rootNavigation.getNavController,
        startDestination = BASE_ROUTE,
        route = ROOT_ROUTE
    ) {
        composable(
            route = BASE_ROUTE,
        ) {
            LocalNavigationState.baseNavigation.setNavController(rememberNavController())
            Dashboard {
                NavHost(
                    navController = LocalNavigationState.baseNavigation.getNavController,
                    startDestination = BASE_ROUTE,
                    route = ROOT_ROUTE
                ) {
                    baseTabNavGraph(
                        appState = LocalNavigationState.baseNavigation,
                        route = BASE_ROUTE,
                    )
                }
            }
        }
        composable(
            route = PRODUCT_ROUTE,
        ) {
            LocalNavigationState.productNavigation.setNavController(rememberNavController())
            Box() {
                NavHost(
                    navController = LocalNavigationState.productNavigation.getNavController,
                    startDestination = PRODUCT_ROUTE,
                    route = ROOT_ROUTE
                ) {
                    productNavGraph(
                        appState = LocalNavigationState.productNavigation,
                        route = PRODUCT_ROUTE,
                    )
                }
            }
        }
    }
}

@Composable
fun ProductListScreen() {
    Box(Modifier.fillMaxSize().background(Color.Red)) {
        Text(modifier = Modifier.align(Alignment.Center), text = "ProductListScreen")
    }
}

@Composable
fun ProductDetailsScreen() {
    Box(Modifier.fillMaxSize().background(Color.Blue)) {
        Text(modifier = Modifier.align(Alignment.Center), text = "ProductDetailsScreen")
    }
}

@Composable
fun Tab1Screen() {
    val random = rememberSaveable() { mutableStateOf(Random.nextInt(0, 100)) }
    val random2 = remember() { mutableStateOf(Random.nextInt(0, 100)) }
    Box(Modifier.fillMaxSize().background(Color.Yellow)) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    LocalNavigationState.productNavigation.setStartDestination(route = Route.ProductList.link)
                    LocalNavigationState.rootNavigation.getNavController.navigate(route = PRODUCT_ROUTE)
                }
            ) {
                Text("Product List")
            }
            Spacer(Modifier.height(20.dp))
            Button(
                onClick = {
                    LocalNavigationState.productNavigation.setStartDestination(route = Route.ProductDetails.link)
                    LocalNavigationState.rootNavigation.getNavController.navigate(route = PRODUCT_ROUTE)
                }
            ) {
                Text("Product Details")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Random : ${random.value}")
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Random2 : ${random2.value}")
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Random3 : ${Random.nextInt(0, 100)}")
        }
    }
}

@Composable
fun Tab2Screen() {
    val random = rememberSaveable() { mutableStateOf(Random.nextInt(0, 100)) }
    val random2 = remember() { mutableStateOf(Random.nextInt(0, 100)) }
    Box(Modifier.fillMaxSize().background(Color.Cyan)) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(text = "Tab2Screen")
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Random : ${random.value}")
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Random2 : ${random2.value}")
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Random3 : ${Random.nextInt(0, 100)}")
        }
    }
}

@Composable
fun Tab3Screen() {
//    val arguments = LocalNavigationState.baseNavigation.getNavController.currentBackStackEntry?.arguments
//    val params = arguments?.getString(NavArguments.tab_3_animals)
//
//    val paramsData = params?.let {
//        AnimalsParametersType.parseValue(it)
//    }
//
//    println(paramsData)
//    var selectedTab = remember {
//        mutableStateOf(paramsData?.tab ?: AnimalsTab.DOG)
//    }
    val random = rememberSaveable() { mutableStateOf(Random.nextInt(0, 100)) }
    val random2 = remember() { mutableStateOf(Random.nextInt(0, 100)) }
    Box(Modifier.fillMaxSize().background(Color.Cyan)) {
//        Column(modifier = Modifier.fillMaxWidth()) {
//            TabRow(
//                selectedTabIndex = selectedTab.value.index,
//                contentColor = Color.Yellow
//            ) {
//                val tabs = AnimalsTab.values()
//                tabs.forEach {
//                    Tab(selected = it == selectedTab.value, text = {
//                        Text(text = it.tabName)
//                    }, onClick = {
//                        selectedTab.value = it
//                    })
//                }
//            }
//            Text("current tab : ${selectedTab.value.tabName}")
//        }
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(text = "Tab3Screen")
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Random : ${random.value}")
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Random2 : ${random2.value}")
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Random3 : ${Random.nextInt(0, 100)}")
        }
    }
}