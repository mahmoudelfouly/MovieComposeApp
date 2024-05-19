package com.melfouly.movieapp.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object MoviesScreenRoute

@Serializable
object SeriesScreenRoute

@Serializable
object ActorsScreenRoute

@Serializable
data class MovieDetailsRoute(val id: Int)