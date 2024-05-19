package com.melfouly.movieapp.presentation

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.melfouly.movieapp.presentation.composable.BottomNavigationBar
import com.melfouly.movieapp.presentation.composable.MainTopBar
import com.melfouly.movieapp.presentation.navigation.HomeNavigationTab
import com.melfouly.movieapp.presentation.navigation.MainTabStateHolder
import com.melfouly.movieapp.presentation.ui.movie.MoviesScreen
import com.melfouly.movieapp.presentation.viewmodel.MainViewModel

@Composable
fun MainTabScreen(
    viewModel: MainViewModel,
    tabStateHolder: MainTabStateHolder,
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

        Crossfade(
            targetState = selectedTab,
            modifier = Modifier
                .padding(start = 4.dp, end = 4.dp, top = topPadding, bottom = botPadding),
            label = ""
        ) { destination ->
            when (destination) {
                HomeNavigationTab.MOVIES -> MoviesScreen(
                    viewModel,
                    tabStateHolder.moviesLazyGridState
                )

                HomeNavigationTab.SERIES -> SeriesScreen()
                HomeNavigationTab.ACTORS -> ActorsScreen()
            }
        }
    }
}