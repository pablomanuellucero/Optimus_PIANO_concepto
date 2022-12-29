package ar.com.optimus.optimuspiano.android.ui.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import ar.com.optimus.optimuspiano.android.ui.TopAppBar
import ar.com.optimus.optimuspiano.android.ui.screens.components.BottomNavigationBar
import ar.com.optimus.optimuspiano.android.ui.screens.navigation.Destinations
import ar.com.optimus.optimuspiano.android.ui.screens.navigation.NavigationHost

@Composable
public fun MainScreen() {
    val navController = rememberNavController()
    val navItems = listOf (
        Destinations.SearchScreen
    )
    Scaffold(
        topBar = { TopAppBar() },
        bottomBar = { BottomNavigationBar(navController = navController, items = navItems) }
    ) { padding->
        NavigationHost(navController, Modifier.padding (padding))
    }
}