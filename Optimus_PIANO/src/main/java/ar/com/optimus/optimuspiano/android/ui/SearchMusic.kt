package ar.com.optimus.optimuspiano.android.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ar.com.optimus.optimuspiano.android.model.MusicItem
import ar.com.optimus.optimuspiano.android.model.getSongs

//@Preview (showBackground = true)

@Composable
public fun SearchMusic(onClick: (MusicItem)->Unit, modifier: Modifier = Modifier) {
    var text by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = modifier
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        var items: List<MusicItem> =
            getSongs().filter { text.isEmpty() || it.title.lowercase().contains(text.lowercase()) }
        MusicList(items, onClick)
    }
}