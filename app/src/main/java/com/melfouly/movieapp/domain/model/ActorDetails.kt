package com.melfouly.movieapp.domain.model

import com.google.gson.annotations.SerializedName

data class ActorDetails(
    val name: String,
    @SerializedName("profile_path")
    val profilePath: String?,
    val birthday: String?,
    @SerializedName("known_for_department")
    val knownForDepartment: String,
    @SerializedName("place_of_birth")
    val placeOfBirth: String?,
    @SerializedName("also_known_as")
    val alsoKnownAs: List<String>?,
    val biography: String
)
