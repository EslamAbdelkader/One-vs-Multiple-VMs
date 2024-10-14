package com.example.myandroidplayground.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myandroidplayground.presentation.businessaccount.BusinessAccountViewModel
import com.example.myandroidplayground.presentation.HeaderUiState
import com.example.myandroidplayground.presentation.header.HeaderViewModel

@Composable
fun AccountHeader(
    parentViewModel: BusinessAccountViewModel = hiltViewModel(),
    viewModel: HeaderViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    uiState?.let {
        AccountHeader(
            uiState = it,
            onRefresh = parentViewModel::refreshData
        )
    }
}

@Composable
private fun AccountHeader(uiState: HeaderUiState, onRefresh: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /* Handle back */ }) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = uiState.accountHeaderTitle, fontSize = 20.sp)
            Text(text = uiState.accountBalance, fontSize = 30.sp)
            Text(
                text = uiState.accountBalanceDescription,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        IconButton(onClick = onRefresh) {
            Icon(Icons.Default.Refresh, contentDescription = "Refresh")
        }
    }
}
