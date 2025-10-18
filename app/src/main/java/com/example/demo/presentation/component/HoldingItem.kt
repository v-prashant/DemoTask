package com.example.demo.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HoldingItem(
    name: String?,
    ltp: Double?,
    netQuantity: Int?,
    profitLoss: Double,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = name?: "",
                modifier = Modifier.fillMaxWidth(0.5f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 20.sp,
                color = Color.Black,
                fontFamily = FontFamily.Serif
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "LTP:  ",
                fontSize = 16.sp,
                color = Color.DarkGray,
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "₹ ${ltp?.toString() ?: ""}",
                fontSize = 20.sp,
                color = Color.Black,
                fontFamily = FontFamily.Serif
            )
        }
        Spacer(modifier = Modifier.fillMaxWidth().padding(top = 24.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Net QTY:  ",
                fontSize = 16.sp,
                color = Color.DarkGray,
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "$netQuantity",
                fontSize = 20.sp,
                color = Color.Black,
                fontFamily = FontFamily.Serif
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "P&L:  ",
                fontSize = 16.sp,
                color = Color.DarkGray,
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "₹ $profitLoss",
                fontSize = 20.sp,
                color = if(profitLoss < 0) Color.Red else Color.Green
            )
        }
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth()
                .padding(top = 16.dp),
            thickness = 1.dp,
            color = Color.LightGray
        )
    }
}

@Preview(showBackground = true, widthDp = 500, heightDp = 900)
@Composable
fun HoldingItemPreview() {
    HoldingItem(
        name = "HDFC",
        ltp = 199.10,
        netQuantity = 3,
        profitLoss = 12.90
    )
}