package com.melfouly.movieapp.data.network

object ApiHelper {

    const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val BASE_POSTER_PATH = "https://image.tmdb.org/t/p/w342"
    private const val YOUTUBE_THUMBNAIL_URL = "https://img.youtube.com/vi/"
    private const val YOUTUBE_VIDEO_URL = "https://www.youtube.com/watch?v="

    fun getPosterPath(path: String?): String {
        return BASE_POSTER_PATH + path
    }

    fun getYoutubeThumbnailPath(path: String?): String {
        return "$YOUTUBE_THUMBNAIL_URL$path/default.jpg"
    }

    fun getYoutubeVideoPath(path: String?): String {
        return YOUTUBE_VIDEO_URL + path
    }
}