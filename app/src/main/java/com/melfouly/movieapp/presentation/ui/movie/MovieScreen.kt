package com.melfouly.movieapp.presentation.ui.movie

import android.annotation.SuppressLint
import android.content.res.Resources.Theme
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.graphics.drawable.ColorDrawable
import android.util.Base64
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.content.res.ResourcesCompat.ThemeCompat
import androidx.palette.graphics.Palette
import coil.request.ImageRequest
import com.kmpalette.color
import com.kmpalette.onColor
import com.melfouly.movieapp.data.network.ApiHelper
import com.melfouly.movieapp.domain.model.DiscoverMoviesResponse
import com.melfouly.movieapp.domain.model.Movie
import com.melfouly.movieapp.presentation.theme.ReversedColorsList
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.palette.PalettePlugin
import com.skydoves.landscapist.palette.rememberPaletteState
import timber.log.Timber

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(
    moviesResponse: DiscoverMoviesResponse,
//    lazyListState: LazyListState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Movies")
                }
            )
        }
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = modifier.statusBarsPadding()
        ) {
            items(moviesResponse.results.size) {
//                Text(text = moviesResponse.results[it].title, fontSize = 12.sp)
                MoviePoster(movie = moviesResponse.results[it])
            }
        }
    }
}

@Composable
fun MoviePoster(
    movie: Movie,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(290.dp)
            .clickable { },
        color = MaterialTheme.colorScheme.onBackground
    ) {
        ConstraintLayout {
            val (box, poster, title) = createRefs()
            var palette by rememberPaletteState(value = null)

            // Poster
            NetworkImage(
                networkUrl = ApiHelper.getPosterPath(movie.posterPath),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .constrainAs(poster) {
                        top.linkTo(parent.top)
                    },
                palette = PalettePlugin(
                    imageModel = ApiHelper.getPosterPath(movie.posterPath),
                    paletteLoadedListener = {
                        palette = it
                        Timber.d("rgb:${it.darkVibrantSwatch?.rgb}")
                    }
                )
            )

            // Title
            Crossfade(
                targetState = palette,
                modifier = Modifier
                    .height(50.dp)
                    .constrainAs(box) {
                        top.linkTo(poster.bottom)
                        bottom.linkTo(parent.bottom)
                    },
                label = ""
            ) {
                Box(
                    modifier = Modifier
                        .background(Color(it?.darkVibrantSwatch?.rgb ?: 0))
                        .alpha(0.7f)
                        .fillMaxSize()
                )
            }
            val darkVibrantSwatch = palette?.darkVibrantSwatch
            val textColor = if (darkVibrantSwatch != null && ReversedColorsList.contains(darkVibrantSwatch.color)) {
                Color(darkVibrantSwatch.titleTextColor)
            } else {
                Color.White
            }
            // Title
            Text(
                text = movie.title,
                color = textColor,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(0.85f)
                    .constrainAs(title) {
                        top.linkTo(box.top)
                        bottom.linkTo(box.bottom)
                    }
            )
        }
    }
}

