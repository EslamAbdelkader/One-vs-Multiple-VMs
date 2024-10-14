// BusinessAccountViewModel.kt
package com.example.myandroidplayground.presentation.latesttransaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myandroidplayground.domain.ObserveBusinessAccountDataUseCase
import com.example.myandroidplayground.presentation.TransactionsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LatestTransactionsViewModel @Inject constructor(
    private val observeBusinessAccountDataUseCase: ObserveBusinessAccountDataUseCase,
    private val mapper: TransactionMapper
) : ViewModel() {

    private val _uiState = MutableStateFlow<TransactionsUiState?>(null)
    val uiState: StateFlow<TransactionsUiState?> = _uiState

    private val _navigationEvent = MutableStateFlow<String?>(null)
    val navigationEvent: StateFlow<String?> = _navigationEvent

    init {
        viewModelScope.launch {
            observeBusinessAccountDataUseCase().collect {
                if (it != null) {
                    _uiState.value = mapper.map(it)
                }
            }
        }
    }

    private fun logAnalyticsEvent(event: String) {
        // Dummy analytics logging
        println("Analytics event logged: $event")
    }

    fun onTransactionClick(id: Int) {
        // Handle the transaction click, e.g., log the event or navigate
        logAnalyticsEvent("Insights Clicked")
        _navigationEvent.value = "Navigate to Transaction with ID: $id"
    }

    fun onNavigationHandled() {
        _navigationEvent.value = null
    }
}
