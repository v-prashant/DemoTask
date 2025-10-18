package com.example.demo.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomSheet(
    currentValue: String,
    totalInvestment: String,
    totalProfitLoss: Double,
    todayProfitLoss: Double,
    totalPercentage: Double,
    onToggle: () -> Unit
) {
    val isExtended = rememberSaveable { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxWidth()
        .padding(16.dp)) {

        if(isExtended.value){
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Current Value*",
                    fontSize = 17.sp,
                    color = Color.DarkGray,
                    fontFamily = FontFamily.Serif,
                    modifier = Modifier.fillMaxWidth(0.6f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "₹ $currentValue",
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontFamily = FontFamily.Serif
                )
            }
            Row(modifier = Modifier.fillMaxWidth()
                .padding(top = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Current investment*",
                    fontSize = 17.sp,
                    color = Color.DarkGray,
                    fontFamily = FontFamily.Serif,
                    modifier = Modifier.fillMaxWidth(0.6f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "₹ $totalInvestment",
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontFamily = FontFamily.Serif
                )
            }
            Row(modifier = Modifier.fillMaxWidth()
                .padding(top = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Today's Profit & Loss*",
                    fontSize = 17.sp,
                    color = Color.DarkGray,
                    fontFamily = FontFamily.Serif,
                    modifier = Modifier.fillMaxWidth(0.6f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "₹ $todayProfitLoss",
                    fontSize = 18.sp,
                    color = if(todayProfitLoss < 0.0) Color.Red else Color.Green,
                    fontFamily = FontFamily.Serif
                )
            }
            HorizontalDivider(
                thickness = 1.dp,
                color = Color.LightGray,
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 12.dp)
            )
        }
        Row(modifier = Modifier.fillMaxWidth()
            .padding(top = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Profit & Loss*",
                fontSize = 17.sp,
                color = Color.DarkGray,
                fontFamily = FontFamily.Serif,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Icon(
                imageVector = if(isExtended.value) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
                contentDescription = "toggle",
                modifier = Modifier.padding(start = 10.dp)
                    .clickable {
                        isExtended.value = !isExtended.value
                        onToggle()
                    }
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "₹ $totalProfitLoss ($totalPercentage)%",
                fontSize = 18.sp,
                color = if(totalProfitLoss < 0.0) Color.Red else Color.Green,
                fontFamily = FontFamily.Serif
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 500, heightDp = 900)
@Composable
fun BottomSheetPreview() {
    BottomSheet(
        currentValue = "27,893",
        totalInvestment = "28,000",
        totalProfitLoss = -1000.0,
        todayProfitLoss = 1200.0,
        totalPercentage = 2.44
    ) {

    }
}