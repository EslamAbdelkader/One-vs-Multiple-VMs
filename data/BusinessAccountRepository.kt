// BusinessAccountRepository.kt
package com.example.myandroidplayground.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.List
import com.example.myandroidplayground.presentation.ActionButtonData
import com.example.myandroidplayground.presentation.ActionEnum
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class BusinessAccountRepository @Inject constructor() {
    private val cache = MutableStateFlow<BusinessAccountData?>(null)

    suspend fun fetchBusinessAccountData() {
        // Simulate network delay
        kotlinx.coroutines.delay(1000)

        // Throw an exception with a 20% probability to simulate an error
        if (Random.nextInt(5) == 0) {
            throw Exception("Random error occurred while fetching business account data")
        }

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
                id = it,
                title = if (amount < 0) "To: Electricity company" else "SumUp pay-in",
                amount = formattedAmount,
                description = description
            )
        }

        val businessAccountData = BusinessAccountData(
            accountHeaderTitle = "Business Account",
            accountBalance = formattedBalance,
            accountBalanceDescription = "Available balance",
            lastTransactionsTitle = "Last transactions",
            transactions = transactions,
            incomingOutgoingTitle = "Incoming vs. Outgoing",
            actionButtons = listOf(
                ActionButtonData(
                    icon = Icons.Default.ArrowForward,
                    label = "Send money",
                    actionEnum = ActionEnum.SEND_MONEY
                ),
                ActionButtonData(
                    icon = Icons.Default.Add,
                    label = "Add money",
                    actionEnum = ActionEnum.ADD_MONEY
                ),
                ActionButtonData(
                    icon = Icons.Default.List,
                    label = "Insights",
                    actionEnum = ActionEnum.INSIGHTS
                )
            )
        )

        // Update the cache
        cache.emit(businessAccountData)
    }

    fun observeBusinessAccountData(): Flow<BusinessAccountData?> = cache
}
