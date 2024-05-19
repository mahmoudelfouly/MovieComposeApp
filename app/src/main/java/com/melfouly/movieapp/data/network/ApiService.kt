package com.melfouly.movieapp.data.network

import com.melfouly.movieapp.domain.model.ActorsResponse
import com.melfouly.movieapp.domain.model.DiscoverMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie?language=en-US&sort_by=popularity.desc")
    suspend fun getMovies(@Query("page") page: Int): Response<DiscoverMoviesResponse>

    @GET("person/popular?language=en-US")
    suspend fun getActors(@Query("page") page: Int): Response<ActorsResponse>
}