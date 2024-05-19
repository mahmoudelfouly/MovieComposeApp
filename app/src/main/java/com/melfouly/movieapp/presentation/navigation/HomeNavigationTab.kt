package com.melfouly.movieapp.presentation.navigation

import com.melfouly.movieapp.R

enum class HomeNavigationTab(
    val title: String,
    val icon: Int
) {
    MOVIES("Movies", R.drawable.movies),
    SERIES("Series", R.drawable.series),
    ACTORS("Actors", R.drawable.actors)
}