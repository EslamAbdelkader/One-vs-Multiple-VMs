package com.example.myandroidplayground

import javax.inject.Inject

class GetBusinessAccountDataUseCase @Inject constructor(
    private val repository: BusinessAccountRepository
) {
    fun execute(): BusinessAccountData {
        return repository.getBusinessAccountData()
    }
}

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
    val title: String,
    val amount: String,
    val description: String,
)
