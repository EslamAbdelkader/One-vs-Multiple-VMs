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

@Composable
fun AccountHeader(uiState: UiState, onRefresh: () -> Unit) {
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
            Text(text = uiState.accountBalanceDescription, fontSize = 14.sp, color = Color.Gray)
        }

        IconButton(onClick = onRefresh) {
            Icon(Icons.Default.Refresh, contentDescription = "Refresh")
        }
    }
}

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
fun LastTransactions(uiState: UiState, onTransactionClick: (Int) -> Unit) {
    Text(text = uiState.lastTransactionsTitle, fontSize = 16.sp)
    uiState.transactions.forEach { transaction ->
        TransactionCard(
            title = transaction.title,
            amount = transaction.amount,
            description = transaction.description,
            amountColor = transaction.amountColor,
            onClick = { onTransactionClick(transaction.id) }
        )
    }
}

@Composable
fun IncomingOutgoingGraph(uiState: UiState) {
    Text(text = uiState.incomingOutgoingTitle, fontSize = 16.sp)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Chart"
        )
    }
}

@Composable
fun TransactionCard(
    title: String,
    amount: String,
    description: String,
    amountColor: Color,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = title, fontSize = 16.sp)
                Text(text = amount, fontSize = 16.sp, color = amountColor)
            }
            Text(text = description, fontSize = 14.sp, color = Color.Gray)
        }
    }
}

@Composable
fun IncomingOutgoingGraph() {
    Text(text = "Incoming vs. Outgoing", fontSize = 16.sp)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Chart"
        )
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
