package com.melfouly.movieapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melfouly.movieapp.domain.model.DiscoverMoviesResponse
import com.melfouly.movieapp.domain.model.NetworkResult
import com.melfouly.movieapp.domain.usecase.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val getMovieUseCase: GetMoviesUseCase) :
    ViewModel() {

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
}