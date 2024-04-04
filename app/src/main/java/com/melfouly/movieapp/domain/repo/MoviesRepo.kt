package com.melfouly.movieapp.domain.repo

import com.melfouly.movieapp.domain.model.DiscoverMoviesResponse
import com.melfouly.movieapp.domain.model.NetworkResult

interface MoviesRepo {

    suspend fun getMovies(page: Int): NetworkResult<DiscoverMoviesResponse>
}