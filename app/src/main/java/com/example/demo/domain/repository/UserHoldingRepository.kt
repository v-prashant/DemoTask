package com.example.demo.domain.repository

import com.example.demo.data.response.UserHoldingResponse

interface UserHoldingRepository {

    suspend fun getUserHolding(): UserHoldingResponse
}