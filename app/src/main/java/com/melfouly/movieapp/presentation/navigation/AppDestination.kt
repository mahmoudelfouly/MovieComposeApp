package com.melfouly.movieapp.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object MoviesScreenRoute

@Serializable
data class MovieDetailsScreenRoute(val id: Long)

@Serializable
data class ActorDetailsScreenRoute(val id: Long)