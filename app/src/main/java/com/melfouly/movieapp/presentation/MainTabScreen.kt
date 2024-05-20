package com.melfouly.movieapp.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.melfouly.movieapp.presentation.composable.BottomNavigationBar
import com.melfouly.movieapp.presentation.composable.MainTopBar
import com.melfouly.movieapp.presentation.navigation.HomeNavigationTab
import com.melfouly.movieapp.presentation.navigation.MainTabStateHolder
import com.melfouly.movieapp.presentation.ui.actor.ActorsScreen
import com.melfouly.movieapp.presentation.ui.movie.MoviesScreen
import com.melfouly.movieapp.presentation.ui.series.SeriesScreen
import com.melfouly.movieapp.presentation.viewmodel.MainViewModel

@Composable
fun MainTabScreen(
    viewModel: MainViewModel,
    tabStateHolder: MainTabStateHolder,
    onNavigateToDetails: (homeNavigationTab: HomeNavigationTab, id: Long) -> Unit
) {

    val selectedTab by viewModel.selectedTab.collectAsState()

    Scaffold(
        topBar = {
            MainTopBar()
        },
        bottomBar = {
            BottomNavigationBar(selectedTab, viewModel::selectTab)
        }
    ) { innerPadding ->
        val topPadding = (innerPadding.calculateTopPadding() + 4.dp)
        val botPadding = (innerPadding.calculateBottomPadding() + 4.dp)

        AnimatedContent(
            targetState = selectedTab,
            modifier = Modifier
                .padding(start = 4.dp, end = 4.dp, top = topPadding, bottom = botPadding),
            label = "",
            transitionSpec = {
                slideIn(tween(500)) {
                    IntOffset(it.width, it.height)
                } togetherWith slideOut(tween(500)) {
                    IntOffset(-it.width, -it.height)
                }
            }
        ) { destination ->
            when (destination) {
                HomeNavigationTab.MOVIES -> MoviesScreen(
                    viewModel,
                    tabStateHolder.moviesLazyGridState
                )

                HomeNavigationTab.SERIES -> SeriesScreen(
                    viewModel,
                    tabStateHolder.seriesLazyGridState
                )

                HomeNavigationTab.ACTORS -> ActorsScreen(
                    viewModel,
                    onNavigateToDetails,
                    tabStateHolder.actorsLazyGridState
                )
            }
        }
    }
}