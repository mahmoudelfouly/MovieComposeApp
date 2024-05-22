package com.melfouly.movieapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melfouly.movieapp.domain.model.NetworkResult
import com.melfouly.movieapp.domain.model.Series
import com.melfouly.movieapp.domain.usecase.GetSeriesDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesDetailsViewModel @Inject constructor(private val useCase: GetSeriesDetailsUseCase) :
    ViewModel() {

    private val _seriesDetails = MutableStateFlow<NetworkResult<Series>>(NetworkResult.Loading)
    val seriesDetails: StateFlow<NetworkResult<Series>> = _seriesDetails.asStateFlow()

    fun getSeriesDetails(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            _seriesDetails.emit(useCase(id))
        }
    }

}