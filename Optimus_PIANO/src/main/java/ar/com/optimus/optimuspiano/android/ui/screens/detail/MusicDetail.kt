package ar.com.optimus.optimuspiano.android.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ar.com.optimus.optimuspiano.android.model.MusicItem
import coil.compose.AsyncImage
import coil.request.ImageRequest
import fromJson
import java.net.URLDecoder

@Composable
fun MusicDetail(item: MusicItem, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentAlignment = Alignment.Center
        ) {
            /*SubcomposeAsyncImage(
                model = item.thumb,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize (),
                contentDescription = null
            ) {
                val state = painter.state
                if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                    CircularProgressIndicator()
                } else {
                    SubcomposeAsyncImageContent()
                }
            }*/
            /*AsyncImage(
                model = item.thumb,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize (),
                contentDescription = null
            )*/
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.thumb)
                    .crossfade(true)
                    .build(),
                //placeholder = painterResource(coil.gif.R.drawable.abc_vector_test),
                contentDescription = item.title, //stringResource(R.string.description),
                //contentScale = ContentScale.Crop,
                modifier = Modifier.requiredSize(250.dp)//.clip(CircleShape)
            )
            if (item.type == MusicItem.Type.SONG) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null,
                    modifier = Modifier.size(92.dp),
                    tint = Color.White
                )
            }
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.secondary)
                .padding(8.dp)
        ) {
            Text(
                text = item.title,
                maxLines = 1,
                softWrap = false,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h4
            )
        }
    }
}

@Composable
fun MusicDetail(json: String, modifier: Modifier = Modifier) {
    val jsondec = URLDecoder.decode(json, "UTF-8")
    MusicDetail(jsondec.fromJson(MusicItem::class.java), modifier)
}