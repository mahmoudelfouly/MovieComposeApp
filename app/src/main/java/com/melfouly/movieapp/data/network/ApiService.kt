package com.melfouly.movieapp.data.network

import com.melfouly.movieapp.domain.model.ActorDetails
import com.melfouly.movieapp.domain.model.ActorsResponse
import com.melfouly.movieapp.domain.model.CombinedCredits
import com.melfouly.movieapp.domain.model.DiscoverMoviesResponse
import com.melfouly.movieapp.domain.model.DiscoverSeriesResponse
import com.melfouly.movieapp.domain.model.Keywords
import com.melfouly.movieapp.domain.model.Movie
import com.melfouly.movieapp.domain.model.Series
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    /**
     * Movies Apis
     */

    @GET("discover/movie?language=en-US&sort_by=popularity.desc")
    suspend fun getMovies(@Query("page") page: Int): Response<DiscoverMoviesResponse>

    @GET("movie/{movie_id}?language=en-US")
    suspend fun getMovieDetails(@Path("movie_id") id: Long): Response<Movie>

    @GET("movie/{movie_id}/keywords")
    suspend fun getMovieKeywords(@Path("movie_id") id: Long): Response<Keywords>

    /**
     * Series Apis
     */

    @GET("discover/tv?language=en-US&sort_by=popularity.desc")
    suspend fun getSeries(@Query("page") page: Int): Response<DiscoverSeriesResponse>

    @GET("tv/{series_id}?language=en-US")
    suspend fun getSeriesDetails(@Path("series_id") id: Long): Response<Series>

    @GET("tv/{series_id}/keywords")
    suspend fun getSeriesKeywords(@Path("series_id") id: Long): Response<Keywords>

    /**
     * Actors Apis
     */

    @GET("person/popular?language=en-US")
    suspend fun getActors(@Query("page") page: Int): Response<ActorsResponse>

    @GET("person/{person_id}?language=en-US")
    suspend fun getActorDetails(@Path("person_id") id: Long): Response<ActorDetails>

    @GET("person/{person_id}/combined_credits")
    suspend fun getActorCombinedCredits(@Path("person_id") id: Long): Response<CombinedCredits>
}