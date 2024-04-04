package com.melfouly.movieapp.domain.model

sealed class NetworkResult<out T : Any> {
    object Loading : NetworkResult<Nothing>()
    data class Success<out T : Any>(val data: T) : NetworkResult<T>()
    data class Failure(var message: String?, var throwable: Throwable? = null) :
        NetworkResult<Nothing>()
}
