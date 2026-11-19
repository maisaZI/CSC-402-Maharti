package com.example.maharti.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.maharti.ui.components.BottomNavigationBar

@Composable
fun MainScreen(navController: NavHostController = rememberNavController()) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val selectedItem = Routes.routeToTab(currentRoute)

    Scaffold(
        bottomBar = {
            if (currentRoute in Routes.bottomBarRoutes) {
                BottomNavigationBar(
                    selectedItem = selectedItem,
                    onItemSelected = { item ->
                        navController.navigateToTab(Routes.tabToRoute(item))
                    }
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            MahartiNavGraph(navController = navController)
        }
    }
}

fun NavHostController.navigateToTab(route: String) {
    navigate(route) {
        popUpTo(Routes.HOME) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}
