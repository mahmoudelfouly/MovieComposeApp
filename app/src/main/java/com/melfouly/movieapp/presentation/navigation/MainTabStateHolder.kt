package com.melfouly.movieapp.presentation.navigation

import androidx.compose.foundation.lazy.grid.LazyGridState

data class MainTabStateHolder(
    val moviesLazyGridState: LazyGridState,
    val seriesLazyGridState: LazyGridState,
    val actorsLazyGridState: LazyGridState
)