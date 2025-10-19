package com.example.demo.presentation.ui.screen.holding

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.demo.data.FakeUserHoldingRepositoryImpl
import com.example.demo.domain.model.UserHoldingModel
import com.example.demo.domain.usecase.UserHoldingUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HoldingViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: HoldingViewModel
    private lateinit var useCase: UserHoldingUseCase
    private lateinit var repository: FakeUserHoldingRepositoryImpl

    @Before
    fun setUp() {
        repository = FakeUserHoldingRepositoryImpl()
        useCase = UserHoldingUseCase(repository)
        viewModel = HoldingViewModel(useCase, mainCoroutineRule.testDispatcher)
    }

    @Test
    fun `test getProfitLoss`() {
        val item = UserHoldingModel(ltp = 100.0, quantity = 10, avgPrice = 90.0, close = 95.0, symbol = "TCS")
        val profitLoss = viewModel.getProfitLoss(item)
        Assert.assertEquals(100.0, profitLoss, 0.0)
    }

    @Test
    fun `getCurrentValue should return correct total current value`() = runTest  {
        mainCoroutineRule.scheduler.advanceUntilIdle()
        val expectedCurrentValue = 79000.0
        val actualCurrentValue = viewModel.getCurrentValue()
        Assert.assertEquals(expectedCurrentValue, actualCurrentValue, 0.01)
    }

    @Test
    fun `getTotalInvestment should return correct total investment value`() = runTest {
        mainCoroutineRule.scheduler.advanceUntilIdle()
        val expectedInvestment = 76500.0
        val actualInvestment = viewModel.getTotalInvestment()
        Assert.assertEquals(expectedInvestment, actualInvestment, 0.01)
    }

    @Test
    fun `getTodayProfitLoss should return correct today's profit and loss`() = runTest {
        mainCoroutineRule.scheduler.advanceUntilIdle()
        val expectedTodayPL = -1150.0
        val actualTodayPL = viewModel.getTodayProfitLoss()
        Assert.assertEquals(expectedTodayPL, actualTodayPL, 0.01)
    }

    @Test
    fun `getTotalProfitLoss should return correct total profit and loss`() = runTest {
        mainCoroutineRule.scheduler.advanceUntilIdle()
        val expectedTotalPL = 2500.0
        val actualTotalPL = viewModel.getTotalProfitLoss()
        Assert.assertEquals(expectedTotalPL, actualTotalPL, 0.01)
    }

    @Test
    fun `getTotalProfitLossPercentage should return correct percentage`() = runTest {
        mainCoroutineRule.scheduler.advanceUntilIdle()
        val expectedPercentage = 3.27
        val actualPercentage = viewModel.getTotalProfitLossPercentage()
        Assert.assertEquals(expectedPercentage, actualPercentage, 0.01)
    }

}