package com.melfouly.movieapp.presentation.ui.movie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.melfouly.movieapp.domain.model.DiscoverMoviesResponse
import com.melfouly.movieapp.presentation.composable.MainTopBar
import com.melfouly.movieapp.presentation.composable.Poster

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(
    moviesResponse: DiscoverMoviesResponse,
//    lazyListState: LazyListState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            MainTopBar()
        }
    ) { innerPadding ->
        val padding = (innerPadding.calculateTopPadding() + 4.dp)
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = modifier.consumeWindowInsets(innerPadding),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            contentPadding = PaddingValues(vertical = padding, horizontal = 4.dp)
        ) {
            items(moviesResponse.results.size) {
                Poster(movie = moviesResponse.results[it])
            }
        }
    }
}

