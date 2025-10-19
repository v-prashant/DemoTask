package com.example.demo.presentation.ui.screen.holding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.demo.presentation.component.BottomSheet
import com.example.demo.presentation.component.HoldingItem
import com.example.demo.presentation.ui.UiState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HoldingScreen() {

    val viewModel: HoldingViewModel = hiltViewModel()
    val holdingState = viewModel.userHoldingState.value

    when(holdingState) {
        is UiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }
        is UiState.Success -> {
            val scaffoldState = rememberBottomSheetScaffoldState()
            val scope = rememberCoroutineScope()
            BottomSheetScaffold(
                scaffoldState = scaffoldState,
                sheetDragHandle = null,
                sheetSwipeEnabled = false,
                sheetContent = {
                    BottomSheet(
                        currentValue = viewModel.getCurrentValue().toString(),
                        totalInvestment = viewModel.getTotalInvestment().toString(),
                        totalProfitLoss = viewModel.getTotalProfitLoss(),
                        todayProfitLoss = viewModel.getTodayProfitLoss(),
                        totalPercentage = viewModel.getTotalProfitLossPercentage()
                    ) {
                        scope.launch {
                            if (scaffoldState.bottomSheetState.currentValue == SheetValue.Expanded) {
                                scaffoldState.bottomSheetState.partialExpand()
                            } else {
                                scaffoldState.bottomSheetState.expand()
                            }
                        }
                    }
                },
            ) { innerPadding->
                LazyColumn(modifier = Modifier.padding(innerPadding)) {
                    items(holdingState.data) { item->
                        HoldingItem(
                            name = item.symbol,
                            ltp = item.ltp,
                            netQuantity = item.quantity,
                            profitLoss = viewModel.getProfitLoss(item)
                        )
                    }
                }
            }
        }
        is UiState.Error -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = holdingState.msg,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif
                )
            }
        }
    }

}