package com.melfouly.movieapp.domain.model

import com.google.gson.annotations.SerializedName

data class Series(
    var page: Int,
    var keywords: List<Keyword>? = emptyList(),
    var videos: List<Video>? = emptyList(),
    var reviews: List<Review>? = emptyList(),
    @SerializedName("poster_path")
    val posterPath: String?,
    val popularity: Float,
    val id: Long,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("vote_average")
    val voteAverage: Float,
    val overview: String,
    @SerializedName("first_air_date")
    val firstAirDate: String?,
    @SerializedName("origin_country")
    val originCountry: List<String>,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("vote_count")
    val voteCount: Int,
    val name: String,
    @SerializedName("original_name")
    val originalName: String
)