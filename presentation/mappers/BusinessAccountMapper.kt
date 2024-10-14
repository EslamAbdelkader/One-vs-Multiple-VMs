// BusinessAccountMapper.kt
package com.example.myandroidplayground.presentation.mappers

import com.example.myandroidplayground.data.BusinessAccountData
import com.example.myandroidplayground.presentation.UiState
import com.example.myandroidplayground.data.UserData
import javax.inject.Inject

class BusinessAccountMapper @Inject constructor(
    private val transactionMapper: TransactionMapper,
    private val actionButtonMapper: ActionButtonMapper,
    private val headerMapper: HeaderMapper
) {
    fun map(data: BusinessAccountData, userData: UserData): UiState {
        val filteredActionButtons = actionButtonMapper.filter(data.actionButtons, userData)
        val transactions = transactionMapper.map(data.transactions)
        val headerData = headerMapper.formatHeaderData(
            title = data.accountHeaderTitle,
            balance = data.accountBalance,
            description = data.accountBalanceDescription,
            userData = userData
        )

        return UiState(
            headerData = headerData,
            lastTransactionsTitle = data.lastTransactionsTitle,
            transactions = transactions,
            incomingOutgoingTitle = data.incomingOutgoingTitle,
            actionButtons = filteredActionButtons
        )
    }
}
