// ObserveAccountOverviewDataUseCase.kt
package com.example.myandroidplayground.domain

import com.example.myandroidplayground.data.AccountOverviewData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class ObserveAccountOverviewDataUseCase @Inject constructor(
    private val observeUserDataUseCase: ObserveUserDataUseCase,
    private val observeBusinessAccountDataUseCase: ObserveBusinessAccountDataUseCase
) {
    operator fun invoke(): Flow<AccountOverviewData?> {
        return observeUserDataUseCase()
            .combine(observeBusinessAccountDataUseCase()) { userData, businessAccountData ->
                println("userData: $userData and businessAccountData: $businessAccountData")
                if (businessAccountData != null && userData != null) {
                    AccountOverviewData(businessAccountData, userData)
                } else null
            }
    }
}
