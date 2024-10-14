// BusinessAccountViewModel.kt
package com.example.myandroidplayground

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BusinessAccountViewModel @Inject constructor(
    private val getBusinessAccountDataUseCase: GetBusinessAccountDataUseCase,
    private val getUserDataUseCase: GetUserDataUseCase,
    private val mapper: BusinessAccountMapper
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState?>(null)
    val uiState: StateFlow<UiState?> = _uiState

    init {
        refreshData()
    }

    fun refreshData() {
        viewModelScope.launch {
            val businessData = getBusinessAccountDataUseCase.execute()
            val userData = getUserDataUseCase.execute()
            val uiState = mapper.map(businessData, userData)
            _uiState.value = uiState
        }
    }
}
