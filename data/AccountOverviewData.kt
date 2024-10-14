package com.example.myandroidplayground.data

import com.example.myandroidplayground.presentation.ActionButtonData

data class AccountOverviewData(
    val businessAccountData: BusinessAccountData,
    val userData: UserData,
)

data class BusinessAccountData(
    val accountHeaderTitle: String,
    val accountBalance: String,
    val accountBalanceDescription: String,
    val lastTransactionsTitle: String,
    val transactions: List<TransactionData>,
    val incomingOutgoingTitle: String,
    val actionButtons: List<ActionButtonData>
)

data class TransactionData(
    val id: Int,
    val title: String,
    val amount: String,
    val description: String,
)
