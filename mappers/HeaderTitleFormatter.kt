// HeaderTitleFormatter.kt
package com.example.myandroidplayground.mappers

import com.example.myandroidplayground.UserData
import javax.inject.Inject

class HeaderTitleFormatter @Inject constructor() {
    fun format(title: String, userData: UserData): String {
        return if (userData.isPremium) "$title (Premium)" else title
    }
}
