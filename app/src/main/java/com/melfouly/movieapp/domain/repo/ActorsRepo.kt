package com.melfouly.movieapp.domain.repo

import com.melfouly.movieapp.domain.model.ActorsResponse
import com.melfouly.movieapp.domain.model.NetworkResult

interface ActorsRepo {

    suspend fun getActors(page: Int): NetworkResult<ActorsResponse>
}