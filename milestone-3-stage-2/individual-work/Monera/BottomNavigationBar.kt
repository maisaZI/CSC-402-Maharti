package edu.iau.cshj.csc402.navbar

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier

import edu.iau.cshj.csc402.navbar.components.BottomNavigationBar
import edu.iau.cshj.csc402.navbar.screens.ExchangesScreen
import edu.iau.cshj.csc402.navbar.screens.HomeScreen
import edu.iau.cshj.csc402.navbar.screens.ProfileScreen
import edu.iau.cshj.csc402.navbar.screens.RequestsScreen
import edu.iau.cshj.csc402.navbar.screens.SearchScreen

@Composable
fun MainScreen() {

    var selectedItem by rememberSaveable {
        mutableStateOf("Home")
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selectedItem = selectedItem,
                onItemSelected = { item ->
                    selectedItem = item
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (selectedItem) {
                "Home" -> HomeScreen()
                "Profile" -> ProfileScreen()
                "Exchanges" -> ExchangesScreen()
                "Search" -> SearchScreen()
                "Requests" -> RequestsScreen()
                else -> HomeScreen()
            }
        }
    }
}
