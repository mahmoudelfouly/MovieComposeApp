package com.melfouly.movieapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melfouly.movieapp.domain.model.Movie
import com.melfouly.movieapp.domain.model.NetworkResult
import com.melfouly.movieapp.domain.usecase.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val useCase: GetMovieDetailsUseCase) :
    ViewModel() {

    private val _movieDetails = MutableStateFlow<NetworkResult<Movie>>(NetworkResult.Loading)
    val movieDetails: StateFlow<NetworkResult<Movie>> = _movieDetails.asStateFlow()

    fun getMovieDetails(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            _movieDetails.emit(useCase(id))
        }
    }

}