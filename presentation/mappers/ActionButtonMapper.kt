// ActionButtonMapper.kt
package com.example.myandroidplayground.presentation.mappers

import com.example.myandroidplayground.data.BusinessAccountData
import com.example.myandroidplayground.data.UserData
import com.example.myandroidplayground.presentation.ActionsUiState
import javax.inject.Inject

class ActionButtonMapper @Inject constructor() {
    fun map(data: BusinessAccountData, userData: UserData): ActionsUiState {
        val actionButtons = data.actionButtons
        return ActionsUiState(
            if (userData.country == "UK") {
                actionButtons
            } else {
                actionButtons.filter { it.label != "Insights" }
            }
        )
    }
}
