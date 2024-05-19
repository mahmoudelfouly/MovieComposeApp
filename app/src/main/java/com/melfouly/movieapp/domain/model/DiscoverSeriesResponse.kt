package com.melfouly.movieapp.domain.model

import com.google.gson.annotations.SerializedName

data class DiscoverSeriesResponse(
    val page: Int,
    val results: ArrayList<Series>,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)