package com.example.infinito.data.network

import com.example.infinito.data.model.InfinitoApiResponse
import retrofit2.http.GET

interface InfinitoApiService {
    @GET("api/v1/infiniTO")
    suspend fun getInfinitoData(): InfinitoApiResponse
}