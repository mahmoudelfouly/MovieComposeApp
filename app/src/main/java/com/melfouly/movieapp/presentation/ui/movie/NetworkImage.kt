package com.melfouly.movieapp.presentation.ui.movie

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun NetworkImage(
    networkUrl: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.FillBounds
) {
    val url = networkUrl ?: return
    CoilImage(
        imageModel = { url },
        loading = {
            Box(modifier = Modifier.matchParentSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        },
        failure = {
            Text(
                text = "Image request failed.",
                modifier = Modifier.fillMaxSize(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    )
}