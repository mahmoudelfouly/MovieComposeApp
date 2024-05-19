package com.melfouly.movieapp.presentation

import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.melfouly.movieapp.presentation.navigation.MainTabStateHolder
import com.melfouly.movieapp.presentation.navigation.NavGraph

@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val tabStateHolder = MainTabStateHolder(
        rememberLazyGridState(),
        rememberLazyGridState(),
        rememberLazyGridState()
    )

    NavGraph(navController = navController, tabStateHolder)

}