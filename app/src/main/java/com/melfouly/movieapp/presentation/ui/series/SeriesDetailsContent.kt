package com.melfouly.movieapp.presentation.ui.series

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.melfouly.movieapp.domain.model.Series
import com.melfouly.movieapp.presentation.composable.BackButton
import com.melfouly.movieapp.presentation.composable.KeywordCard
import com.melfouly.movieapp.presentation.composable.NetworkImage
import com.melfouly.movieapp.presentation.composable.Overview
import com.melfouly.movieapp.presentation.composable.RateBar

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SeriesDetailsContent(
    seriesDetails: Series,
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
                NetworkImage(networkUrl = ApiHelper.getPosterPath(seriesDetails.posterPath))
            }
            Text(
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 8.dp),
                text = seriesDetails.name,
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            seriesDetails.firstAirDate?.let { date ->
                Text(
                    modifier = modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(2.dp),
                    text = "First Air Date: $date",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )

                seriesDetails.voteAverage?.let { avgRate ->
                    RateBar(
                        rate = avgRate,
                        modifier = modifier.align(Alignment.CenterHorizontally)
                    )
                }

                seriesDetails.keywords?.let { list ->
                    FlowRow(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 32.dp)
                    ) {
                        list.forEach { keyword ->
                            KeywordCard(name = keyword.name)
                        }
                    }
                }

                seriesDetails.overview?.let {
                    Overview(title = "Overview", desc = it)
                }


            }
        }

        BackButton(modifier = Modifier.align(Alignment.TopStart), onBackPressed)
    }
}