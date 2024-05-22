package com.melfouly.movieapp.domain.repo

import com.melfouly.movieapp.domain.model.DiscoverSeriesResponse
import com.melfouly.movieapp.domain.model.NetworkResult
import com.melfouly.movieapp.domain.model.Series

interface SeriesRepo {

    suspend fun getSeries(page: Int): NetworkResult<DiscoverSeriesResponse>

    suspend fun getSeriesDetails(id: Long): NetworkResult<Series>
}