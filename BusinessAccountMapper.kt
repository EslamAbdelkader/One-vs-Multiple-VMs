// BusinessAccountMapper.kt
package com.example.myandroidplayground

import androidx.compose.ui.graphics.Color
import javax.inject.Inject

class BusinessAccountMapper @Inject constructor() {

    fun map(data: BusinessAccountData, userData: UserData): UiState {
        val filteredActionButtons = filterActionButtons(data.actionButtons, userData)
        val transactions = mapTransactions(data.transactions)
        val formattedBalance = formatBalance(data.accountBalance)
        val accountHeaderTitle = formatHeaderTitle(data.accountHeaderTitle, userData)

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

    private fun filterActionButtons(actionButtons: List<ActionButtonData>, userData: UserData): List<ActionButtonData> {
        return if (userData.country == "UK") {
            actionButtons
        } else {
            actionButtons.filter { it.label != "Insights" }
        }
    }

    private fun mapTransactions(transactions: List<TransactionData>): List<Transaction> {
        return transactions.map { transaction ->
            val amountColor = if (transaction.amount.startsWith("-")) Color.Red else Color.Blue
            val formattedDescription = formatDescription(transaction.description)
            Transaction(
                title = transaction.title,
                amount = transaction.amount,
                description = formattedDescription,
                amountColor = amountColor
            )
        }
    }

    private fun formatBalance(balance: String): String {
        // Example of additional formatting logic
        return if (balance.startsWith("£")) balance else "£$balance"
    }

    private fun formatHeaderTitle(title: String, userData: UserData): String {
        // Example of conditional logic based on user data
        return if (userData.isPremium) "$title (Premium)" else title
    }

    private fun formatDescription(description: String): String {
        // Example of additional transformation logic
        return description.replace("•", "-")
    }
}
