package com.melfouly.movieapp.presentation.ui.actor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.ui.unit.dp
import com.melfouly.movieapp.domain.model.ActorsResponse
import com.melfouly.movieapp.domain.model.NetworkResult
import com.melfouly.movieapp.presentation.composable.ActorPoster
import com.melfouly.movieapp.presentation.navigation.HomeNavigationTab
import com.melfouly.movieapp.presentation.viewmodel.MainViewModel

@Composable
fun ActorsScreen(
    viewModel: MainViewModel,
    onNavigateToDetails: (homeNavigationTab: HomeNavigationTab, id: Long) -> Unit,
    lazyGridState: LazyGridState,
    modifier: Modifier = Modifier
) {

    viewModel.getActors(1)
    val actorsResponse by viewModel.actorsList.collectAsState()

    when (actorsResponse) {
        is NetworkResult.Loading -> {
            Box(modifier = modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = modifier.align(Alignment.Center)
                )
            }
        }

        is NetworkResult.Success -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = modifier.fillMaxSize(),
                state = lazyGridState,
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                contentPadding = PaddingValues(vertical = 4.dp, horizontal = 4.dp)
            ) {
                items((actorsResponse as NetworkResult.Success<ActorsResponse>).data.results.size) {
                    ActorPoster(
                        actor = (actorsResponse as NetworkResult.Success<ActorsResponse>).data.results[it],
                        onNavigateToDetails = onNavigateToDetails
                    )
                }
            }
        }

        is NetworkResult.Failure -> {
            Box(modifier = modifier.fillMaxSize()) {
                Text(
                    text = "Something went wrong.",
                    modifier = modifier.align(Alignment.Center),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

    }
}