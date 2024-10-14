// BusinessAccountRepository.kt
package com.example.myandroidplayground

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.List
import javax.inject.Inject
import kotlin.random.Random

class BusinessAccountRepository @Inject constructor() {
    fun getBusinessAccountData(): BusinessAccountData {
        // Generate a random balance between 100 and 1000
        val randomBalance = Random.nextDouble(100.0, 1000.0)
        val formattedBalance = "£%.2f".format(randomBalance)

        // Generate random transactions
        val transactions = List(5) {
            val amount = Random.nextDouble(-100.0, 100.0)
            val formattedAmount = if (amount < 0) {
                "-£%.2f".format(-amount)
            } else {
                "+£%.2f".format(amount)
            }
            val description = if (amount < 0) {
                "Upcoming • Due on..."
            } else {
                "15:13 • PID ${Random.nextInt(100000, 999999)}"
            }

            TransactionData(
                title = if (amount < 0) "To: Electricity company" else "SumUp pay-in",
                amount = formattedAmount,
                description = description
            )
        }

        return BusinessAccountData(
            accountHeaderTitle = "Business Account",
            accountBalance = formattedBalance,
            accountBalanceDescription = "Available balance",
            lastTransactionsTitle = "Last transactions",
            transactions = transactions,
            incomingOutgoingTitle = "Incoming vs. Outgoing",
            actionButtons = listOf(
                ActionButtonData(icon = Icons.Default.ArrowForward, label = "Send money"),
                ActionButtonData(icon = Icons.Default.Add, label = "Add money"),
                ActionButtonData(icon = Icons.Default.List, label = "Insights")
            )
        )
    }
}
