package ar.com.optimus.optimuspiano.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import ar.com.optimus.optimuspiano.Greeting
import ar.com.optimus.optimuspiano.android.model.MusicItem
import ar.com.optimus.optimuspiano.android.model.getSongs
import coil.compose.*
import io.ktor.client.*

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
                    MusicList ()
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

//@Preview (showBackground = true, widthDp = 200, heightDp = 100)
@Composable
fun TestComposable () {
    Column {
        AsyncImage(
            model ="https://i.picsum.photos/id/766/200/300.jpg?hmac=yPmyGIdCe3ag8jlW87DzVijW_xLn1vzaiwrJvIChFcM",
            contentDescription = null
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center

        ) {
            Text(
                text = "Hola Optimus!",
                modifier = Modifier
                    .background(Color.Yellow)
                    .border(width = 2.dp, color = Color.Blue)
                    .padding(horizontal = 16.dp, vertical = 10.dp)
                    .border(width = 2.dp, color = Color.Green)
                    .background(Color.Gray)
                    .padding(horizontal = 16.dp, vertical = 10.dp)
                    .border(width = 2.dp, color = Color.Red)
            )
        }
    }
}

//@Preview (showBackground = true)
@Composable
fun MusicListItem (item: MusicItem) {
    Column {
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .height (400.dp),
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
            AsyncImage(
                model = item.thumb,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize (),
                contentDescription = null
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
                .padding (16.dp)
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.h6
            )
        }
    }
}

@Preview (showBackground = true)
@Composable
fun MusicList () {
    LazyColumn (
        contentPadding = PaddingValues(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ){
        items (getSongs ()) {item->
            MusicListItem(item)
        }
    }
}