package com.example.demo.presentation.ui.screen.holding

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demo.data.response.toDto
import com.example.demo.domain.ResultState
import com.example.demo.domain.model.UserHoldingModel
import com.example.demo.domain.usecase.UserHoldingUseCase
import com.example.demo.presentation.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.math.round

@HiltViewModel
class HoldingViewModel @Inject constructor(
    private val userHoldingUseCase: UserHoldingUseCase,
    private val ioDispatcher: CoroutineDispatcher
): ViewModel() {

    private val _userHoldingState = mutableStateOf<UiState<List<UserHoldingModel>>>(UiState.Success(emptyList()))
    val userHoldingState: State<UiState<List<UserHoldingModel>>> = _userHoldingState

    init {
        getHodling()
    }

    fun getHodling() {
        _userHoldingState.value = UiState.Loading
        viewModelScope.launch {
            withContext(ioDispatcher) {
                val res = userHoldingUseCase()
                when(res) {
                    is ResultState.Success -> {
                        val resToDto = res.data.data?.userHolding?.map {
                            it.toDto()
                        } ?: emptyList()
                        _userHoldingState.value = UiState.Success(resToDto)
                    }
                    is ResultState.Error -> {
                        _userHoldingState.value = UiState.Error(msg = res.msg)
                    }
                }
            }
        }
    }

    fun getProfitLoss(item: UserHoldingModel): Double {
        val pl = ((item.ltp ?: 0.0) * (item.quantity ?: 0))-((item.avgPrice ?: 0.0) *(item.quantity ?: 0))
        return round(pl*100)/100
    }

    fun getCurrentValue(): Double {
        val data = (_userHoldingState.value as? UiState.Success)?.data
        val res = data?.sumOf { item->
            (item.ltp ?: 0.0) * (item.quantity ?: 0 )
        } ?: 0.0
        return round(res*100)/100
    }

    fun getTotalInvestment(): Double {
        val data = (_userHoldingState.value as? UiState.Success)?.data
        val res = data?.sumOf { item->
            (item.avgPrice ?: 0.0) * (item.quantity ?: 0 )
        } ?: 0.0
        return round(res*100)/100
    }

    fun getTotalProfitLoss(): Double {
        val res = getCurrentValue() - getTotalInvestment()
        return round(res*100)/100
    }

    fun getTodayProfitLoss(): Double {
        val data = (_userHoldingState.value as? UiState.Success)?.data
        val res = data?.sumOf { item->
            ((item.close ?: 0.0) - (item.ltp ?: 0.0))*(item.quantity ?: 0)
        } ?: 0.0
        return round(res*100)/100
    }

    fun getTotalProfitLossPercentage(): Double {
        val totalInvestment = getTotalInvestment()
        val totalProfitLoss = getTotalProfitLoss()

        if (totalInvestment == 0.0) {
            return 0.0
        }
        val percentage = (totalProfitLoss / totalInvestment) * 100
        return round(percentage * 100) / 100
    }

}