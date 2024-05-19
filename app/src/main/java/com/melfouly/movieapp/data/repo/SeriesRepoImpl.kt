package com.melfouly.movieapp.data.repo

import com.melfouly.movieapp.data.network.ApiService
import com.melfouly.movieapp.domain.model.DiscoverSeriesResponse
import com.melfouly.movieapp.domain.model.NetworkResult
import com.melfouly.movieapp.domain.repo.SeriesRepo
import retrofit2.HttpException
import java.io.IOException

class SeriesRepoImpl(private val apiService: ApiService) : SeriesRepo {

    override suspend fun getSeries(page: Int): NetworkResult<DiscoverSeriesResponse> {
        return try {
            val response = apiService.getSeries(page).body()!!
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