// TransactionMapper.kt
package com.example.myandroidplayground.presentation.mappers

import androidx.compose.ui.graphics.Color
import com.example.myandroidplayground.presentation.Transaction
import com.example.myandroidplayground.data.TransactionData
import javax.inject.Inject

class TransactionMapper @Inject constructor() {
    fun map(transactions: List<TransactionData>): List<Transaction> {
        return transactions.map { transaction ->
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
    }

    private fun formatDescription(description: String): String {
        return description.replace("•", "-")
    }
}
