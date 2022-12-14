package ar.com.optimus.optimuspiano.android.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ar.com.optimus.optimuspiano.android.MyApplicationTheme

@Composable
public fun SongsApp(content: @Composable () -> Unit) {
    MyApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}
