package com.melfouly.movieapp.domain.model

import com.google.gson.annotations.SerializedName

data class Actor(
    @SerializedName("profile_path")
    val profilePath: String?,
    val adult: Boolean,
    val id: Long,
    val name: String,
    val popularity: Float,
)
