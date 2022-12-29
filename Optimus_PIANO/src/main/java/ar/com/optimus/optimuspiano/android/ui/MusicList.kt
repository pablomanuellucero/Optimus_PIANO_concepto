package ar.com.optimus.optimuspiano.android.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ar.com.optimus.optimuspiano.android.model.MusicItem


//@Preview(showBackground = true)
@Composable
fun MusicList(items: List<MusicItem>, onClick: (MusicItem) -> Unit) {
    LazyVerticalGrid(
        contentPadding = PaddingValues(4.dp),
        columns = GridCells.Adaptive(minSize = 204.dp)
    ) {
        items(items) { itm ->
            MusicListItem(itm, onClick, Modifier.padding(2.dp))
        }
    }
}
