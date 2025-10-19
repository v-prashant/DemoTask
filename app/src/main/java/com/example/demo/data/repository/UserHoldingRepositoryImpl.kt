package com.example.demo.data.repository

import com.example.demo.data.local.LocalService
import com.example.demo.data.remote.ApiService
import com.example.demo.data.response.UserHoldingResponse
import com.example.demo.domain.repository.UserHoldingRepository
import jakarta.inject.Inject
import okio.IOException

class UserHoldingRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val localService: LocalService
) : UserHoldingRepository {

    override suspend fun getUserHolding(): UserHoldingResponse {
        return try {
            val res = apiService.getUserHolding()
            localService.saveUserHolding(res)
            res
        } catch (_: Exception) {
            val localRes = localService.getUserHolding()
            if(localRes != null)
                return localRes
            throw IOException("No Internet!")
        }
    }

}