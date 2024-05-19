package com.melfouly.movieapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melfouly.movieapp.domain.model.DiscoverMoviesResponse
import com.melfouly.movieapp.domain.model.NetworkResult
import com.melfouly.movieapp.domain.usecase.GetMoviesUseCase
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
class MainViewModel @Inject constructor(private val getMovieUseCase: GetMoviesUseCase): ViewModel() {

    private val _selectedTab = MutableStateFlow(HomeNavigationTab.MOVIES)
    val selectedTab: StateFlow<HomeNavigationTab> = _selectedTab.asStateFlow()

    private val _moviesList =
        MutableStateFlow<NetworkResult<DiscoverMoviesResponse>>(NetworkResult.Loading)
    val moviesList: StateFlow<NetworkResult<DiscoverMoviesResponse>> get() = _moviesList

    init {
        getMovies(1)
    }

    fun getMovies(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _moviesList.emit(getMovieUseCase.invoke(page))
        }
    }

    fun selectTab(tab: HomeNavigationTab) {
        _selectedTab.update { tab }
    }

}