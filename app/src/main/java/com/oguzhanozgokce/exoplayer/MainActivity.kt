package com.oguzhanozgokce.exoplayer

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.oguzhanozgokce.exoplayer.ui.theme.EXOPlayerTheme
import com.oguzhanozgokce.exoplayer.util.VideoPlayerExo

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EXOPlayerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val videoUrl =
    "https://firebasestorage.googleapis.com/v0/b/healthandprediction.appspot.com/o/SLIDE%20in%20360%C2%B0%20_%20VR%20_%204K.mp4?alt=media&token=2f1f743f-9daf-4d6f-b846-7c7f6b1e3842"

    Column {
        /*YouTubePlayer(
            youtubeVideoId = "kShAS6aafOU",
            lifecycleOwner = LocalLifecycleOwner.current
        )
        Spacer(modifier = Modifier.height(16.dp))
        VideoPlayer(videoUri =videoUri)*/
//        VideoPlayerExo(videoUrl =videoUrl)
        VideoPlayer360(uri = Uri.parse(videoUrl))
    }

}

@Composable
fun VideoPlayer360(uri: Uri) {
    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.fromUri(uri)
            setMediaItem(mediaItem)
            prepare()
        }
    }

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = {
            PlayerView(context).apply {
                player = exoPlayer
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EXOPlayerTheme {
        Greeting("Android")
    }
}