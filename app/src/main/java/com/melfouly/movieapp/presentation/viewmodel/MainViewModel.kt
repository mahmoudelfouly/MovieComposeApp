package com.melfouly.movieapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melfouly.movieapp.domain.model.ActorsResponse
import com.melfouly.movieapp.domain.model.DiscoverMoviesResponse
import com.melfouly.movieapp.domain.model.DiscoverSeriesResponse
import com.melfouly.movieapp.domain.model.NetworkResult
import com.melfouly.movieapp.domain.usecase.GetActorsUseCase
import com.melfouly.movieapp.domain.usecase.GetMoviesUseCase
import com.melfouly.movieapp.domain.usecase.GetSeriesUseCase
import com.melfouly.movieapp.presentation.navigation.HomeNavigationTab
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getSeriesUseCase: GetSeriesUseCase,
    private val getActorsUseCase: GetActorsUseCase
): ViewModel() {

    private val _selectedTab = MutableStateFlow(HomeNavigationTab.MOVIES)
    val selectedTab: StateFlow<HomeNavigationTab> = _selectedTab.asStateFlow()

    private val _moviesList =
        MutableStateFlow<NetworkResult<DiscoverMoviesResponse>>(NetworkResult.Loading)
    val moviesList: StateFlow<NetworkResult<DiscoverMoviesResponse>> = _moviesList.asStateFlow()

    private val _seriesList =
        MutableStateFlow<NetworkResult<DiscoverSeriesResponse>>(NetworkResult.Loading)
    val seriesList: StateFlow<NetworkResult<DiscoverSeriesResponse>> = _seriesList.asStateFlow()

    private val _actorsList =
        MutableStateFlow<NetworkResult<ActorsResponse>>(NetworkResult.Loading)
    val actorsList: StateFlow<NetworkResult<ActorsResponse>> = _actorsList.asStateFlow()

    init {
        getMovies(1)
    }

    fun getMovies(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _moviesList.emit(getMoviesUseCase(page))
        }
    }

    fun getSeries(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _seriesList.emit(getSeriesUseCase(page))
        }
    }

    fun getActors(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _actorsList.emit(getActorsUseCase(page))
        }
    }

    fun selectTab(tab: HomeNavigationTab) {
        _selectedTab.update { tab }
    }

}