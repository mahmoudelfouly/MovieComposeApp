package com.melfouly.movieapp.data.repo

import com.melfouly.movieapp.data.network.ApiService
import com.melfouly.movieapp.domain.model.DiscoverMoviesResponse
import com.melfouly.movieapp.domain.model.Movie
import com.melfouly.movieapp.domain.model.NetworkResult
import com.melfouly.movieapp.domain.repo.MoviesRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import retrofit2.HttpException
import java.io.IOException

class MoviesRepoImpl(private val apiService: ApiService) : MoviesRepo {

    override suspend fun getMovies(page: Int): NetworkResult<DiscoverMoviesResponse> {
        return try {
            val response = apiService.getMovies(page).body()!!
            NetworkResult.Success(response)
        } catch (e: HttpException) {
            //handles exception with the request
            NetworkResult.Failure(e.message, e)
        } catch (e: IOException) {
            //handles no internet exception
            NetworkResult.Failure(e.message, e)
        }

    }

    override suspend fun getMovieDetails(id: Long): NetworkResult<Movie> {
        return try {
            coroutineScope {
                val detailsResponse =
                    async(Dispatchers.IO) { apiService.getMovieDetails(id).body()!! }.await()
                val keywordsResponse =
                    async(Dispatchers.IO) { apiService.getMovieKeywords(id).body()!! }.await()
                val videosResponse =
                    async(Dispatchers.IO) { apiService.getMovieVideos(id).body()!! }.await()

                detailsResponse.keywords = keywordsResponse.keywords
                detailsResponse.videos = videosResponse.results
                NetworkResult.Success(detailsResponse)
            }
        } catch (e: HttpException) {
            //handles exception with the request
            NetworkResult.Failure(e.message, e)
        } catch (e: IOException) {
            //handles no internet exception
            NetworkResult.Failure(e.message, e)
        }
    }
}