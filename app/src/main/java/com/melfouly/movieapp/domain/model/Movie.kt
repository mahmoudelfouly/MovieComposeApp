package com.melfouly.movieapp.domain.model

import com.google.gson.annotations.SerializedName

data class Movie(
    var page: Int,
    var keywords: List<Keyword>? = ArrayList(),
    var videos: List<Video>? = ArrayList(),
    var reviews: List<Review>? = ArrayList(),
    @SerializedName("poster_path")
    val posterPath: String?,
    val adult: Boolean,
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    val id: Long,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    val title: String,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    val popularity: Float,
    @SerializedName("vote_count")
    val voteCount: Int,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Float
)
