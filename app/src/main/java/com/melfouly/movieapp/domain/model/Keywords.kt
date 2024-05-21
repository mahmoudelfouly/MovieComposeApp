package com.melfouly.movieapp.domain.model

data class Keywords(
    val id: Long,
    var keywords: List<Keyword>? = ArrayList(),
)
