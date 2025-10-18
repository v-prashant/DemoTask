package com.example.demo.data

import com.example.demo.data.response.UserHoldingResponse
import retrofit2.http.GET

interface ApiService {

    @GET("/")
    suspend fun getUserHolding(): UserHoldingResponse

}