package com.melfouly.movieapp.presentation.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.palette.PalettePlugin

@Composable
fun NetworkImage(
    networkUrl: String?,
    modifier: Modifier = Modifier,
    palette: PalettePlugin? = null,
    contentScale: ContentScale = ContentScale.FillBounds
) {
    val url = networkUrl ?: return

    CoilImage(
        imageModel = { url },
        modifier = modifier,
        component = rememberImageComponent {
            palette?.let { add(it) }
        },
        loading = {
            Box(modifier = Modifier.matchParentSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        },
        failure = {
            Box(modifier = Modifier.matchParentSize()) {
                Text(
                    text = "Image request failed.",
                    color = Color.Black,
                    modifier = Modifier
                        .align(Alignment.Center),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    )
}