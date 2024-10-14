package com.example.myandroidplayground.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myandroidplayground.presentation.UiState

@Composable
fun LastTransactions(uiState: UiState, onTransactionClick: (Int) -> Unit) {
    Text(text = uiState.lastTransactionsTitle, fontSize = 16.sp)
    uiState.transactions.forEach { transaction ->
        TransactionCard(
            title = transaction.title,
            amount = transaction.amount,
            description = transaction.description,
            amountColor = transaction.amountColor,
            onClick = { onTransactionClick(transaction.id) }
        )
    }
}

@Composable
fun TransactionCard(
    title: String,
    amount: String,
    description: String,
    amountColor: Color,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = title, fontSize = 16.sp)
                Text(text = amount, fontSize = 16.sp, color = amountColor)
            }
            Text(text = description, fontSize = 14.sp, color = Color.Gray)
        }
    }
}
