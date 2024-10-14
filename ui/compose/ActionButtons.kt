package com.example.myandroidplayground.ui.compose

import android.widget.Toast
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myandroidplayground.presentation.ActionEnum
import com.example.myandroidplayground.presentation.ActionsUiState
import com.example.myandroidplayground.presentation.ActionsViewModel

@Composable
fun ActionButtons(
    viewModel: ActionsViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
    val navigationEvent by viewModel.navigationEvent.collectAsState()

    uiState?.let {
        ActionButtons(
            uiState = it,
            onSendMoneyClick = viewModel::onSendMoneyClick,
            onAddMoneyClick = viewModel::onAddMoneyClick,
            onInsightsClick = viewModel::onInsightsClick
        )
    }

    navigationEvent?.let { event ->
        Toast.makeText(context, event, Toast.LENGTH_SHORT).show()
        viewModel.onNavigationHandled()
    }
}

@Composable
private fun ActionButtons(
    uiState: ActionsUiState,
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
