// BusinessAccountViewModel.kt
package com.example.myandroidplayground

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myandroidplayground.mappers.BusinessAccountMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BusinessAccountViewModel @Inject constructor(
    private val fetchAccountOverviewData: FetchAccountOverviewData,
    private val observeBusinessAccountDataUseCase: ObserveBusinessAccountDataUseCase,
    private val mapper: BusinessAccountMapper
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState?>(null)
    val uiState: StateFlow<UiState?> = _uiState

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _navigationEvent = MutableStateFlow<String?>(null)
    val navigationEvent: StateFlow<String?> = _navigationEvent

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        viewModelScope.launch {
            observeBusinessAccountDataUseCase().collect { accountOverviewData ->
                if (accountOverviewData != null) {
                    _uiState.value = mapper.map(
                        accountOverviewData.businessAccountData,
                        accountOverviewData.userData
                    )
                }
            }
        }
        refreshData()
    }

    fun refreshData() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                fetchAccountOverviewData()
            } catch (e: Exception) {
                _errorMessage.value = "Failed to refresh data: ${e.message}"
            } finally {
                _isLoading.value = false
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

    fun onTransactionClick(id: Int) {
        // Handle the transaction click, e.g., log the event or navigate
        logAnalyticsEvent("Insights Clicked")
        _navigationEvent.value = "Navigate to Transaction with ID: $id"
    }

    fun onNavigationHandled() {
        _navigationEvent.value = null
    }

    fun onErrorHandled() {
        _errorMessage.value = null
    }
}
