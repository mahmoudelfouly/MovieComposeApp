package com.melfouly.movieapp.domain.model

import com.google.gson.annotations.SerializedName

data class ActorsResponse(
    val page: Int,
    var results: ArrayList<Actor>,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)
