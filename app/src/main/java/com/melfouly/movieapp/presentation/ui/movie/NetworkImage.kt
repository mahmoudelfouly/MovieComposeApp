package com.melfouly.movieapp.presentation.ui.movie

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.palette.graphics.Palette
import coil.ImageLoader
import com.melfouly.movieapp.data.network.ApiHelper
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.ImageComponent
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.palette.PalettePlugin
import com.skydoves.landscapist.palette.rememberPaletteState
import timber.log.Timber

@Composable
fun NetworkImage(
    networkUrl: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.FillBounds
) {
    val url = networkUrl ?: return
    var palette by rememberPaletteState(value = null)

    CoilImage(
        imageModel = { url },
        modifier = modifier,
        component = rememberImageComponent {
            PalettePlugin(
                imageModel = ApiHelper.getPosterPath(networkUrl),
                useCache = true, // use cache strategies for the same image model.
                paletteLoadedListener = {
                    palette = it
                }
            )
        },
        loading = {
            Box(modifier = Modifier.matchParentSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        },
//        success = {data, y ->
//            Box(modifier = Modifier.fillMaxSize()) {
//                Text(text = data.imageBitmap.toString())
//            }
//        },
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