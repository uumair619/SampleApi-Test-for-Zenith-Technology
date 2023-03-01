package com.clean.sampleapi.api

import retrofit2.http.GET

interface SampleApiService {

    @GET("products")
    suspend fun getProducts(
    ): ProductResponse

}