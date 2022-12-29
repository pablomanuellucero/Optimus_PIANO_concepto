package ar.com.optimus.optimuspiano.android.ui.screens.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ar.com.optimus.optimuspiano.android.ui.SearchMusic
import ar.com.optimus.optimuspiano.android.ui.screens.detail.MusicDetail

@Composable
fun NavigationHost(navController: NavHostController, padding: Modifier) {

    NavHost(navController = navController, startDestination = Destinations.SearchScreen.route) {
        composable(Destinations.SearchScreen.route) {
            SearchMusic (onClick = {item->
                    navController.navigate(Destinations.DetailScreen.createRoute(item))
            },
                padding)
        }
        composable(Destinations.DetailScreen.route) { navBackStackEntry ->
            val key = navBackStackEntry.arguments?.getString("key")
            requireNotNull(key)
            MusicDetail(key, padding)
        }
    }

}