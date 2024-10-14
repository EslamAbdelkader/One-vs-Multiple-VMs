package com.example.myandroidplayground.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myandroidplayground.presentation.ActionEnum
import com.example.myandroidplayground.R
import com.example.myandroidplayground.presentation.UiState

@Composable
fun BusinessAccountScreen(
    uiState: UiState,
    onRefresh: () -> Unit,
    onSendMoneyClick: () -> Unit,
    onAddMoneyClick: () -> Unit,
    onInsightsClick: () -> Unit,
    onTransactionClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AccountHeader(uiState, onRefresh)
        Spacer(modifier = Modifier.height(20.dp))
        ActionButtons(uiState, onSendMoneyClick, onAddMoneyClick, onInsightsClick)
        Spacer(modifier = Modifier.height(20.dp))
        LastTransactions(uiState, onTransactionClick)
        Spacer(modifier = Modifier.height(20.dp))
        IncomingOutgoingGraph(uiState)
    }
}
