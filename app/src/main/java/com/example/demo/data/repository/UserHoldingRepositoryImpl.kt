package com.example.demo.data.repository

import com.example.demo.data.ApiService
import com.example.demo.data.response.UserHoldingResponse
import com.example.demo.domain.repository.UserHoldingRepository
import jakarta.inject.Inject

class UserHoldingRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UserHoldingRepository {

    override suspend fun getUserHolding(): UserHoldingResponse {
         return apiService.getUserHolding()
    }

}