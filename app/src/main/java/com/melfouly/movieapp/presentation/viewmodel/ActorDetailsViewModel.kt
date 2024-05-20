package com.melfouly.movieapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melfouly.movieapp.domain.model.ActorDetails
import com.melfouly.movieapp.domain.model.NetworkResult
import com.melfouly.movieapp.domain.usecase.GetActorDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActorDetailsViewModel @Inject constructor(private val useCase: GetActorDetailsUseCase) :
    ViewModel() {

    private val _actorDetails = MutableStateFlow<NetworkResult<ActorDetails>>(NetworkResult.Loading)
    val actorDetails: StateFlow<NetworkResult<ActorDetails>> = _actorDetails.asStateFlow()

    fun getActorDetails(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            _actorDetails.emit(useCase(id))
        }
    }

}