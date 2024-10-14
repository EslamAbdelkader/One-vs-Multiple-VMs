// BalanceFormatter.kt
package com.example.myandroidplayground.mappers

import javax.inject.Inject

class BalanceFormatter @Inject constructor() {
    fun format(balance: String): String {
        return if (balance.startsWith("£")) balance else "£$balance"
    }
}
