package com.example.myandroidplayground.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myandroidplayground.presentation.UiState

@Composable
fun BusinessAccountScreen(
    uiState: UiState,
) {
    if (uiState is UiState.Loading) {
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AccountHeader()
        Spacer(modifier = Modifier.height(20.dp))
        ActionButtons()
        Spacer(modifier = Modifier.height(20.dp))
        LastTransactions()
    }
}
