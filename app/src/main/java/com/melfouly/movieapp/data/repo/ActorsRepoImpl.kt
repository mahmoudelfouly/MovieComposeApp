package com.melfouly.movieapp.data.repo

import com.melfouly.movieapp.data.network.ApiService
import com.melfouly.movieapp.domain.model.ActorDetails
import com.melfouly.movieapp.domain.model.ActorsResponse
import com.melfouly.movieapp.domain.model.NetworkResult
import com.melfouly.movieapp.domain.repo.ActorsRepo
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

class ActorsRepoImpl(private val apiService: ApiService) : ActorsRepo {

    override suspend fun getActors(page: Int): NetworkResult<ActorsResponse> {
        return try {
            val response = apiService.getActors(page).body()!!
            Timber.d("$response")
            NetworkResult.Success(response)
        } catch (e: HttpException) {
            //handles exception with the request
            NetworkResult.Failure(e.message, e)
        } catch (e: IOException) {
            //handles no internet exception
            NetworkResult.Failure(e.message, e)
        }
    }

    override suspend fun getActorDetails(id: Long): NetworkResult<ActorDetails> {
        return try {
            val response = apiService.getActorDetails(id).body()!!
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