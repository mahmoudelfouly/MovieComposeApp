package com.melfouly.movieapp.domain.repo

import com.melfouly.movieapp.domain.model.DiscoverSeriesResponse
import com.melfouly.movieapp.domain.model.NetworkResult

interface SeriesRepo {

    suspend fun getSeries(page: Int): NetworkResult<DiscoverSeriesResponse>
}