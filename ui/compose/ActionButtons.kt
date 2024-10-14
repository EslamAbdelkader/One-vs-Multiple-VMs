package com.example.myandroidplayground.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myandroidplayground.presentation.ActionEnum
import com.example.myandroidplayground.presentation.UiState

@Composable
fun ActionButtons(
    uiState: UiState,
    onSendMoneyClick: () -> Unit,
    onAddMoneyClick: () -> Unit,
    onInsightsClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        uiState.actionButtons.forEach { actionButton ->
            val action = when (actionButton.actionEnum) {
                ActionEnum.SEND_MONEY -> onSendMoneyClick
                ActionEnum.ADD_MONEY -> onAddMoneyClick
                ActionEnum.INSIGHTS -> onInsightsClick
            }
            ActionButton(icon = actionButton.icon, label = actionButton.label, action)
        }
    }
}

@Composable
fun ActionButton(icon: ImageVector, label: String, onClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(50),
            modifier = Modifier.size(50.dp)
        ) {
            Icon(icon, contentDescription = label)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = label, fontSize = 12.sp)
    }
}
