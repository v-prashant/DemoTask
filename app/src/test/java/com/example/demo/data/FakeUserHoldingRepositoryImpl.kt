package com.example.demo.data

import com.example.demo.data.response.UserHolding
import com.example.demo.data.response.UserHoldingData
import com.example.demo.data.response.UserHoldingResponse
import com.example.demo.domain.repository.UserHoldingRepository

class FakeUserHoldingRepositoryImpl : UserHoldingRepository {

    override suspend fun getUserHolding(): UserHoldingResponse {
        return UserHoldingResponse(
            data = UserHolding(
                userHolding = listOf(
                    UserHoldingData(
                        symbol = "TCS",
                        quantity = 10,
                        ltp = 3500.0,
                        avgPrice = 3200.0,
                        close = 3450.0
                    ),
                    UserHoldingData(
                        symbol = "RELIANCE",
                        quantity = 5,
                        ltp = 2800.0,
                        avgPrice = 2700.0,
                        close = 2750.0
                    ),
                    UserHoldingData(
                        symbol = "HDFCBANK",
                        quantity = 20,
                        ltp = 1500.0,
                        avgPrice = 1550.0,
                        close = 1480.0
                    )
                )
            )
        )
    }

}