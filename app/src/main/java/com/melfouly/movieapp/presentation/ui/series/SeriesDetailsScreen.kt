package com.melfouly.movieapp.presentation.ui.series

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.melfouly.movieapp.domain.model.NetworkResult
import com.melfouly.movieapp.domain.model.Series
import com.melfouly.movieapp.presentation.composable.BackButton
import com.melfouly.movieapp.presentation.viewmodel.SeriesDetailsViewModel

@Composable
fun SeriesDetailsScreen(
    viewModel: SeriesDetailsViewModel,
    id: Long,
    onBackPressed: (Boolean) -> Unit
) {
    viewModel.getSeriesDetails(id)
    val seriesDetails by viewModel.seriesDetails.collectAsState()

    when (seriesDetails) {
        is NetworkResult.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        is NetworkResult.Success -> {

            val scrollState = rememberScrollState()

            SeriesDetailsContent(
                seriesDetails = (seriesDetails as NetworkResult.Success<Series>).data,
                scrollState = scrollState,
                onBackPressed = onBackPressed
            )
        }


        is NetworkResult.Failure -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Something went wrong.",
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium
                )

                BackButton(modifier = Modifier.align(Alignment.TopStart), onBackPressed)
            }
        }
    }

}