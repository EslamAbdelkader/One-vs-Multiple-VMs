// HeaderAndBalanceFormatter.kt
package com.example.myandroidplayground.presentation.mappers

import com.example.myandroidplayground.data.UserData
import com.example.myandroidplayground.presentation.HeaderData
import javax.inject.Inject

class HeaderMapper @Inject constructor() {
    fun formatHeaderData(
        title: String,
        balance: String,
        description: String,
        userData: UserData
    ): HeaderData {
        val formattedBalance = if (balance.startsWith("£")) balance else "£$balance"
        val formattedTitle = if (userData.isPremium) "$title (Premium)" else title
        return HeaderData(
            accountHeaderTitle = formattedTitle,
            accountBalance = formattedBalance,
            accountBalanceDescription = description
        )
    }
}
