package com.oguzhanozgokce.exoplayer.util

import android.net.Uri
import android.widget.MediaController
import android.widget.VideoView
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun VideoPlayer(
    uri: Uri,
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier.fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(16 / 9f),
        factory = { context ->
            VideoView(context).apply {
                setVideoURI(uri)

                val myController = MediaController(context)
                myController.setAnchorView(this)
                setMediaController(myController)

                setOnPreparedListener {
                    start()
                }
            }
        })
}