package com.melfouly.movieapp.presentation.ui.movie

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.melfouly.movieapp.data.network.ApiHelper
import com.melfouly.movieapp.domain.model.Movie
import com.melfouly.movieapp.presentation.composable.NetworkImage
import com.melfouly.movieapp.presentation.composable.RateBar
import timber.log.Timber

@Composable
fun MovieDetailsContent(
    movieDetails: Movie,
    scrollState: ScrollState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Box(modifier = modifier.fillMaxWidth()) {
            NetworkImage(networkUrl = ApiHelper.getPosterPath(movieDetails.posterPath))
        }
        Text(
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp),
            text = movieDetails.title,
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        movieDetails.releaseDate?.let { date ->
            Text(
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(2.dp),
                text = "Release Date: $date",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )

            movieDetails.voteAverage?.let { avgRate ->
                RateBar(
                    rate = avgRate,
                    modifier = modifier.align(Alignment.CenterHorizontally)
                )
            }


        }
    }
}