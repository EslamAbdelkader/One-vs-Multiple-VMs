// ActionButtonMapper.kt
package com.example.myandroidplayground.mappers

import com.example.myandroidplayground.ActionButtonData
import com.example.myandroidplayground.UserData
import javax.inject.Inject

class ActionButtonMapper @Inject constructor() {
    fun filter(actionButtons: List<ActionButtonData>, userData: UserData): List<ActionButtonData> {
        return if (userData.country == "UK") {
            actionButtons
        } else {
            actionButtons.filter { it.label != "Insights" }
        }
    }
}
