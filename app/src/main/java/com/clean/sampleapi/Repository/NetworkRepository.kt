package com.clean.sampleapi.Repository

import com.clean.sampleapi.api.NetworkResult
import com.clean.sampleapi.api.SampleApiService
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NetworkRepository @Inject constructor(private val apiService: SampleApiService) {

    suspend fun getProduct() = flow {
        emit(NetworkResult.Loading(true))
        val response = apiService.getProducts()
        emit(NetworkResult.Success(response.products))
    }.catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }
   /* suspend fun getPosts()  = flow {
        emit(NetworkResult.Loading(true))
        val response = apiService.getPosts()
        emit(NetworkResult.Success(response.items))
    }.catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }*/
}