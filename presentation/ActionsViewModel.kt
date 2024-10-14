// BusinessAccountViewModel.kt
package com.example.myandroidplayground.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myandroidplayground.domain.ObserveAccountOverviewDataUseCase
import com.example.myandroidplayground.presentation.mappers.ActionButtonMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActionsViewModel @Inject constructor(
    private val observeAccountOverviewDataUseCase: ObserveAccountOverviewDataUseCase,
    private val mapper: ActionButtonMapper
) : ViewModel() {

    private val _uiState = MutableStateFlow<ActionsUiState?>(null)
    val uiState: StateFlow<ActionsUiState?> = _uiState

    private val _navigationEvent = MutableStateFlow<String?>(null)
    val navigationEvent: StateFlow<String?> = _navigationEvent

    init {
        viewModelScope.launch {
            observeAccountOverviewDataUseCase().collect {
                if (it != null) {
                    _uiState.value = mapper.map(
                        data = it.businessAccountData,
                        userData = it.userData
                    )
                }
            }
        }
    }

    private fun logAnalyticsEvent(event: String) {
        // Dummy analytics logging
        println("Analytics event logged: $event")
    }

    fun onSendMoneyClick() {
        logAnalyticsEvent("Send Money Clicked")
        _navigationEvent.value = "Navigate to Send Money Screen"
    }

    fun onAddMoneyClick() {
        logAnalyticsEvent("Add Money Clicked")
        _navigationEvent.value = "Navigate to Add Money Screen"
    }

    fun onInsightsClick() {
        logAnalyticsEvent("Insights Clicked")
        _navigationEvent.value = "Navigate to Insights Screen"
    }

    fun onNavigationHandled() {
        _navigationEvent.value = null
    }
}
