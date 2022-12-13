package ar.com.optimus.optimuspiano.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.com.optimus.optimuspiano.android.model.MusicItem
import ar.com.optimus.optimuspiano.android.model.getSongs
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors(
            primary = Color(0xFFBB86FC),
            primaryVariant = Color(0xFF3700B3),
            secondary = Color(0xFF03DAC5)
        )
    } else {
        lightColors(
            primary = Color(0xFF6200EE),
            primaryVariant = Color(0xFF3700B3),
            secondary = Color(0xFF03DAC5)
        )
    }
    val typography = Typography(
        body1 = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //Greeting(Greeting().greeting())
                    //SearchMusic()
                    Scaffold (
                        topBar = {
                            TopAppBar(
                                title = {
                                    Row {
                                        Text(text = stringResource(id = R.string.app_name))
                                        Spacer(modifier = Modifier.width(20.dp))
                                        Icon (Icons.Default.Create, contentDescription = null)
                                    }
                                }
                            )
                        }
                    ) { padding ->
                        SearchMusic (modifier = Modifier.padding (padding))
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(text: String) {
    Text(text = text)
}

//@Preview (showBackground = true, widthDp = 200, heightDp = 100)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        Greeting("Hello, Android!")
    }
}

//@Preview (showBackground = true)
@Composable
fun MusicListItem(item: MusicItem, modifier: Modifier = Modifier) {
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
                style = MaterialTheme.typography.h6
            )
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun MusicList(items: List<MusicItem>) {
    LazyVerticalGrid(
        contentPadding = PaddingValues(4.dp),
        columns = GridCells.Adaptive(minSize = 204.dp)
    ) {
        items(items) { itm ->
            MusicListItem(itm, Modifier.padding(2.dp))
        }
    }
}

@Composable
fun SearchMusic (modifier: Modifier = Modifier) {
    var text by rememberSaveable {mutableStateOf ("")}
    Column (
        modifier = modifier
    ) {
        TextField (
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        var items: List<MusicItem> = getSongs().filter{text.isEmpty() || it.title.lowercase().contains(text.lowercase())}
        MusicList (items)
    }
}