package com.melfouly.movieapp.presentation.ui.movie

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.melfouly.movieapp.data.network.ApiHelper
import com.melfouly.movieapp.domain.model.DiscoverMoviesResponse
import com.melfouly.movieapp.domain.model.Movie

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(
    moviesResponse: DiscoverMoviesResponse,
//    lazyListState: LazyListState,
    modifier: Modifier = Modifier
) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "Movies")
        })
    }) {
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
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

            NetworkImage(
                networkUrl = ApiHelper.getPosterPath(movie.posterPath),
                modifier = modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .constrainAs(poster) {
                        top.linkTo(poster.top)
                    }
            )
        }
    }
}

