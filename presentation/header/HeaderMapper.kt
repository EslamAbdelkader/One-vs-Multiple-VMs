// HeaderAndBalanceFormatter.kt
package com.example.myandroidplayground.presentation.header

import com.example.myandroidplayground.data.BusinessAccountData
import com.example.myandroidplayground.data.UserData
import com.example.myandroidplayground.presentation.HeaderUiState
import javax.inject.Inject

class HeaderMapper @Inject constructor() {
    fun map(
        businessAccountData: BusinessAccountData,
        userData: UserData
    ): HeaderUiState {
        val balance = businessAccountData.accountBalance
        val title = businessAccountData.accountHeaderTitle
        val description = businessAccountData.accountBalanceDescription

        val formattedBalance = if (balance.startsWith("£")) balance else "£$balance"
        val formattedTitle = if (userData.isPremium) "$title (Premium)" else title
        return HeaderUiState(
            accountHeaderTitle = formattedTitle,
            accountBalance = formattedBalance,
            accountBalanceDescription = description
        )
    }
}
