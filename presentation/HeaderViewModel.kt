// BusinessAccountViewModel.kt
package com.example.myandroidplayground.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myandroidplayground.domain.ObserveAccountOverviewDataUseCase
import com.example.myandroidplayground.presentation.mappers.HeaderMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeaderViewModel @Inject constructor(
    private val observeAccountOverviewDataUseCase: ObserveAccountOverviewDataUseCase,
    private val mapper: HeaderMapper
) : ViewModel() {

    private val _uiState = MutableStateFlow<HeaderUiState?>(null)
    val uiState: StateFlow<HeaderUiState?> = _uiState

    init {
        println("eslam - $this")

        viewModelScope.launch {
            observeAccountOverviewDataUseCase().collect {
                println("eslam - $it")
                if (it != null) {
                    _uiState.value = mapper.map(
                        businessAccountData = it.businessAccountData,
                        userData = it.userData
                    )
                }
            }
        }
    }
}
