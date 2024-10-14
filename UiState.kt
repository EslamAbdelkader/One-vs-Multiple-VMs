package com.example.myandroidplayground

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class UiState(
    val accountHeaderTitle: String,
    val accountBalance: String,
    val accountBalanceDescription: String,
    val lastTransactionsTitle: String,
    val transactions: List<Transaction>,
    val incomingOutgoingTitle: String,
    val actionButtons: List<ActionButtonData>
)

data class Transaction(
    val title: String,
    val amount: String,
    val description: String,
    val amountColor: Color
)

data class ActionButtonData(
    val icon: ImageVector,
    val label: String
)
