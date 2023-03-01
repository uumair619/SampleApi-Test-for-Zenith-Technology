package com.clean.sampleapi.api

import com.clean.sampleapi.model.Product
import com.google.gson.annotations.SerializedName

data class ProductResponse (
    @SerializedName("products") val products: List<Product>,
    @SerializedName("total") val total: Long,
    @SerializedName("skip")val skip: Long,
    @SerializedName("limit") val limit: Long
)