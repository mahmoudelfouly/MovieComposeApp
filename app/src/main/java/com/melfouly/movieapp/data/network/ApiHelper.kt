package com.melfouly.movieapp.data.network

object ApiHelper {

    const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val BASE_POSTER_PATH = "https://image.tmdb.org/t/p/w342"
    private const val BASE_BACKDROP_PATH = "https://image.tmdb.org/t/p/w780"

    fun getPosterPath(path: String?): String {
        return BASE_POSTER_PATH + path
    }

    fun getBackDropPath(path: String?): String {
        return BASE_BACKDROP_PATH + path
    }
}