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
) : ViewModel() {

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

    private var moviesPage = 1
    private var seriesPage = 1
    private var actorsPage = 1
    private var isLoading = false

    init {
        getMovies(moviesPage)
        getSeries(seriesPage)
        getActors(actorsPage)
    }

    private fun getMovies(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading = true
            val result = getMoviesUseCase(page)
            isLoading = false
            if (result is NetworkResult.Success) {
                val currentList =
                    (_moviesList.value as? NetworkResult.Success)?.data?.results ?: arrayListOf()
                currentList.addAll(result.data.results)
                _moviesList.emit(NetworkResult.Success(result.data.copy(results = currentList)))
            } else {
                _moviesList.emit(result)
            }
        }
    }

    fun getNextMoviesPage() {
        if (!isLoading) {
            moviesPage += 1
            getMovies(moviesPage)
        }
    }

    private fun getSeries(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading = true
            val result = getSeriesUseCase(page)
            isLoading = false
            if (result is NetworkResult.Success) {
                val currentList =
                    (_seriesList.value as? NetworkResult.Success)?.data?.results ?: arrayListOf()
                currentList.addAll(result.data.results)
                _seriesList.emit(NetworkResult.Success(result.data.copy(results = currentList)))
            } else {
                _seriesList.emit(result)
            }
        }
    }

    fun getNextSeriesPage() {
        if (!isLoading) {
            seriesPage += 1
            getSeries(seriesPage)
        }
    }

    private fun getActors(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading = true
            val result = getActorsUseCase(page)
            isLoading = false
            if (result is NetworkResult.Success) {
                val currentList =
                    (_actorsList.value as? NetworkResult.Success)?.data?.results ?: arrayListOf()
                currentList.addAll(result.data.results)
                _actorsList.emit(NetworkResult.Success(result.data.copy(results = currentList)))
            } else {
                _actorsList.emit(result)
            }
        }
    }

    fun getNextActorsPage() {
        if (!isLoading) {
            actorsPage += 1
            getActors(actorsPage)
        }
    }

    fun selectTab(tab: HomeNavigationTab) {
        _selectedTab.update { tab }
    }

}