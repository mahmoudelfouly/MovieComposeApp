package com.melfouly.movieapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.melfouly.movieapp.presentation.MainTabScreen
import com.melfouly.movieapp.presentation.navigation.HomeNavigationTab.*
import com.melfouly.movieapp.presentation.ui.actor.ActorDetailsScreen

@Composable
fun NavGraph(navController: NavHostController, tabStateHolder: MainTabStateHolder) {

    NavHost(navController = navController, startDestination = MoviesScreenRoute) {

        composable<MoviesScreenRoute> {
            MainTabScreen(
                viewModel = hiltViewModel(),
                tabStateHolder = tabStateHolder,
                onNavigateToDetails = { tab, id ->
                    when(tab) {
                        MOVIES -> null
                        SERIES -> null
                        ACTORS -> navController.navigate(ActorDetailsScreenRoute(id))
                    }
                }
            )
        }

        composable<ActorDetailsScreenRoute> { backStackEntry ->
            val route: ActorDetailsScreenRoute = backStackEntry.toRoute()
            ActorDetailsScreen(viewModel = hiltViewModel(), route.id)
        }

    }

}