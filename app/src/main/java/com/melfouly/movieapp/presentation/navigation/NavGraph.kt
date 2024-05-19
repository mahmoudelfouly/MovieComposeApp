package com.melfouly.movieapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.melfouly.movieapp.presentation.MainTabScreen

@Composable
fun NavGraph(navController: NavHostController, tabStateHolder: MainTabStateHolder) {

    NavHost(navController = navController, startDestination = MoviesScreenRoute) {

        composable<MoviesScreenRoute> {
            MainTabScreen(viewModel = hiltViewModel(), tabStateHolder = tabStateHolder)
        }

    }

}