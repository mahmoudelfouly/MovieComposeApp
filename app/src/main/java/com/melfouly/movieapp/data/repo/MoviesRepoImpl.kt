package com.melfouly.movieapp.data.repo

import com.melfouly.movieapp.data.network.ApiService
import com.melfouly.movieapp.domain.model.DiscoverMoviesResponse
import com.melfouly.movieapp.domain.model.Movie
import com.melfouly.movieapp.domain.model.NetworkResult
import com.melfouly.movieapp.domain.repo.MoviesRepo
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
            val response = apiService.getMovieDetails(id).body()!!
            NetworkResult.Success(response)
        } catch (e: HttpException) {
            //handles exception with the request
            NetworkResult.Failure(e.message, e)
        } catch (e: IOException) {
            //handles no internet exception
            NetworkResult.Failure(e.message, e)
        }
    }
}