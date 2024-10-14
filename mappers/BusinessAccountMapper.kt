// BusinessAccountMapper.kt
package com.example.myandroidplayground.mappers

import com.example.myandroidplayground.BusinessAccountData
import com.example.myandroidplayground.UiState
import com.example.myandroidplayground.UserData
import javax.inject.Inject

class BusinessAccountMapper @Inject constructor(
    private val transactionMapper: TransactionMapper,
    private val actionButtonMapper: ActionButtonMapper,
    private val balanceFormatter: BalanceFormatter,
    private val headerTitleFormatter: HeaderTitleFormatter
) {
    fun map(data: BusinessAccountData, userData: UserData): UiState {
        val filteredActionButtons = actionButtonMapper.filter(data.actionButtons, userData)
        val transactions = transactionMapper.map(data.transactions)
        val formattedBalance = balanceFormatter.format(data.accountBalance)
        val accountHeaderTitle = headerTitleFormatter.format(data.accountHeaderTitle, userData)

        return UiState(
            accountHeaderTitle = accountHeaderTitle,
            accountBalance = formattedBalance,
            accountBalanceDescription = data.accountBalanceDescription,
            lastTransactionsTitle = data.lastTransactionsTitle,
            transactions = transactions,
            incomingOutgoingTitle = data.incomingOutgoingTitle,
            actionButtons = filteredActionButtons
        )
    }
}
