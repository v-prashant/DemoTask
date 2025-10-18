package com.example.demo.data.response

import com.example.demo.domain.model.UserHoldingModel
import kotlinx.serialization.Serializable

@Serializable
data class UserHoldingResponse(
    val data: UserHolding?
)

@Serializable
data class UserHolding (
    val userHolding: List<UserHoldingData>
)

@Serializable
data class UserHoldingData(
    val avgPrice: Double?,
    val close: Double?,
    val ltp: Double?,
    val quantity: Int?,
    val symbol: String?
)

fun UserHoldingData.toDto(): UserHoldingModel {
    return UserHoldingModel(
        avgPrice = avgPrice,
        close = close,
        ltp = ltp,
        quantity = quantity,
        symbol = symbol
    )
}