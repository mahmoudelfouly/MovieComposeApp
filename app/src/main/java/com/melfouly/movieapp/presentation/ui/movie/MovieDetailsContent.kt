package com.melfouly.movieapp.presentation.ui.movie

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.verticalScroll
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
import com.melfouly.movieapp.presentation.composable.BackButton
import com.melfouly.movieapp.presentation.composable.KeywordCard
import com.melfouly.movieapp.presentation.composable.NetworkImage
import com.melfouly.movieapp.presentation.composable.Overview
import com.melfouly.movieapp.presentation.composable.RateBar
import com.melfouly.movieapp.presentation.composable.VideoPoster

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MovieDetailsContent(
    movieDetails: Movie,
    scrollState: ScrollState,
    modifier: Modifier = Modifier,
    onBackPressed: (Boolean) -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
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
            }

            movieDetails.voteAverage?.let { avgRate ->
                RateBar(
                    rate = avgRate,
                    modifier = modifier.align(Alignment.CenterHorizontally)
                )
            }

            if (!movieDetails.videos.isNullOrEmpty()) {
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = "Trailers:",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                )

                LazyRow(
                    modifier = Modifier.padding(
                        top = 2.dp,
                        bottom = 12.dp,
                        start = 8.dp,
                        end = 8.dp
                    ),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    contentPadding = PaddingValues(4.dp)
                ) {
                    items(movieDetails.videos!!.size) { index ->
                        VideoPoster(video = movieDetails.videos!![index])
                    }
                }
            }

            movieDetails.keywords?.let { list ->
                FlowRow(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 32.dp)
                ) {
                    list.forEach { keyword ->
                        KeywordCard(name = keyword.name)
                    }
                }
            }

            if (!movieDetails.overview.isNullOrBlank()) {
                Overview(title = "Overview", desc = movieDetails.overview)
            }


        }

        BackButton(modifier = Modifier.align(Alignment.TopStart), onBackPressed)
    }
}