package ar.com.optimus.optimuspiano.android.ui.screens.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import ar.com.optimus.optimuspiano.android.model.MusicItem
import toJson
import java.net.URLEncoder

sealed class Destinations(
    val route: String,
    val title: String,
    val icon: ImageVector
) {

    object SearchScreen : Destinations("search", "Búsqueda", Icons.Filled.Search)
    object DetailScreen : Destinations("detail/{key}", "Detalle", Icons.Filled.FavoriteBorder) {
        fun createRoute(item: MusicItem): String {
            val json = item.toJson()
            requireNotNull(json)
            val key = URLEncoder.encode(json, "utf-8")
            return "detail/$key"
        }
    }
}