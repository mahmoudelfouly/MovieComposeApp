package com.melfouly.movieapp.domain.repo

import com.melfouly.movieapp.domain.model.ActorDetails
import com.melfouly.movieapp.domain.model.ActorsResponse
import com.melfouly.movieapp.domain.model.NetworkResult

interface ActorsRepo {

    suspend fun getActors(page: Int): NetworkResult<ActorsResponse>

    suspend fun getActorDetails(id: Long): NetworkResult<ActorDetails>
}