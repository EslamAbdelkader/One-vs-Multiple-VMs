package com.example.myandroidplayground

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class ObserveBusinessAccountDataUseCase @Inject constructor(
    private val repository: BusinessAccountRepository,
    private val userRepository: UserRepository,
) {
    operator fun invoke(): Flow<AccountOverviewData?> {
        return userRepository.observeUserData()
            .combine(repository.observeBusinessAccountData()) { userData, businessAccountData ->
                println("userData: $userData and businessAccountData: $businessAccountData")
                if (businessAccountData != null && userData != null) {
                    AccountOverviewData(businessAccountData, userData)
                } else null
            }
    }
}

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
