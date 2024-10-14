package com.example.myandroidplayground.presentation

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

// UiState.kt

sealed class UiState(
) {
    data object Loading : UiState()
    data object Loaded : UiState()
}

data class TransactionsUiState(
    val lastTransactionsTitle: String,
    val transactions: List<Transaction>
)

data class Transaction(
    val id: Int,
    val title: String,
    val amount: String,
    val description: String,
    val amountColor: Color
)

data class ActionsUiState(
    val actionButtons: List<ActionButtonData>
)

data class ActionButtonData(
    val icon: ImageVector,
    val label: String,
    val actionEnum: ActionEnum
)

enum class ActionEnum {
    SEND_MONEY,
    ADD_MONEY,
    INSIGHTS
}

data class HeaderUiState(
    val accountHeaderTitle: String,
    val accountBalance: String,
    val accountBalanceDescription: String
)
