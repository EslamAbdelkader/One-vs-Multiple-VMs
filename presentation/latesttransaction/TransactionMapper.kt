// TransactionMapper.kt
package com.example.myandroidplayground.presentation.latesttransaction

import androidx.compose.ui.graphics.Color
import com.example.myandroidplayground.data.BusinessAccountData
import com.example.myandroidplayground.presentation.Transaction
import com.example.myandroidplayground.presentation.TransactionsUiState
import javax.inject.Inject

class TransactionMapper @Inject constructor() {
    fun map(data: BusinessAccountData): TransactionsUiState {
        val lastTransactionsTitle = data.lastTransactionsTitle
        val transactions = data.transactions.map { transaction ->
            val amountColor = if (transaction.amount.startsWith("-")) Color.Red else Color.Blue
            val formattedDescription = formatDescription(transaction.description)
            Transaction(
                id = transaction.id,
                title = transaction.title,
                amount = transaction.amount,
                description = formattedDescription,
                amountColor = amountColor
            )
        }

        return TransactionsUiState(
            lastTransactionsTitle = lastTransactionsTitle,
            transactions = transactions
        )
    }

    private fun formatDescription(description: String): String {
        return description.replace("•", "-")
    }
}
