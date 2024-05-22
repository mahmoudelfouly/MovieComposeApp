package com.melfouly.movieapp.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object MainScreenRoute

@Serializable
data class MovieDetailsScreenRoute(val id: Long)

@Serializable
data class SeriesDetailsScreenRoute(val id: Long)

@Serializable
data class ActorDetailsScreenRoute(val id: Long)