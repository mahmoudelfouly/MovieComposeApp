package com.melfouly.movieapp.domain.model

import com.google.gson.annotations.SerializedName

data class DiscoverMoviesResponse(
    val page: Int,
    val results: ArrayList<Movie>,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)
