package com.melfouly.movieapp.data.repo

import com.melfouly.movieapp.data.network.ApiService
import com.melfouly.movieapp.domain.model.DiscoverSeriesResponse
import com.melfouly.movieapp.domain.model.NetworkResult
import com.melfouly.movieapp.domain.model.Series
import com.melfouly.movieapp.domain.repo.SeriesRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
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

    override suspend fun getSeriesDetails(id: Long): NetworkResult<Series> {
        return try {
            coroutineScope {
                val detailsResponse =
                    async(Dispatchers.IO) { apiService.getSeriesDetails(id).body()!! }.await()
                val keywordResponse =
                    async(Dispatchers.IO) { apiService.getSeriesKeywords(id).body()!! }.await()
                val videosResponse =
                    async(Dispatchers.IO) { apiService.getSeriesVideos(id).body()!! }.await()
                detailsResponse.keywords = keywordResponse.keywords
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