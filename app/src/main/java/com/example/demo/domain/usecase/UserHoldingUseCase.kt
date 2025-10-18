package com.example.demo.domain.usecase

import com.example.demo.data.response.UserHoldingResponse
import com.example.demo.domain.ResultState
import com.example.demo.domain.repository.UserHoldingRepository
import okio.IOException
import java.lang.Exception
import javax.inject.Inject

class UserHoldingUseCase @Inject constructor(
    private val repository: UserHoldingRepository
) {

    suspend operator fun invoke(): ResultState<UserHoldingResponse> {
        return try {
            val res = repository.getUserHolding()
            ResultState.Success(data = res)
        } catch (ex: IOException) {
            ResultState.Error(msg = "No Internet")
        } catch (ex: Exception) {
            ResultState.Error(msg = ex.message.toString())
        }
    }
}