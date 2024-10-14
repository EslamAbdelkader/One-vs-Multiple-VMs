package com.example.myandroidplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myandroidplayground.ui.theme.MyAndroidPlaygroundTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: BusinessAccountViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAndroidPlaygroundTheme {
                val uiState = viewModel.uiState.collectAsState().value

                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    uiState?.let {
                        BusinessAccountScreen(uiState = it, onRefresh = { viewModel.refreshData() })
                    }
                }
            }
        }
    }
}

@Composable
fun BusinessAccountScreen(uiState: UiState, onRefresh: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AccountHeader(uiState, onRefresh)
        Spacer(modifier = Modifier.height(20.dp))
        ActionButtons(uiState)
        Spacer(modifier = Modifier.height(20.dp))
        LastTransactions(uiState)
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
fun ActionButtons(uiState: UiState) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        uiState.actionButtons.forEach { actionButton ->
            ActionButton(icon = actionButton.icon, label = actionButton.label)
        }
    }
}

@Composable
fun LastTransactions(uiState: UiState) {
    Text(text = uiState.lastTransactionsTitle, fontSize = 16.sp)
    uiState.transactions.forEach { transaction ->
        TransactionCard(
            title = transaction.title,
            amount = transaction.amount,
            description = transaction.description,
            amountColor = transaction.amountColor
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
fun TransactionCard(title: String, amount: String, description: String, amountColor: Color) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
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
fun ActionButton(icon: ImageVector, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = { /* Handle click */ },
            shape = RoundedCornerShape(50),
            modifier = Modifier.size(50.dp)
        ) {
            Icon(icon, contentDescription = label)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = label, fontSize = 12.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBusinessAccountScreen() {
    val sampleUiState = UiState(
        accountHeaderTitle = "Business Account",
        accountBalance = "£265,13",
        accountBalanceDescription = "Available balance",
        lastTransactionsTitle = "Last transactions",
        transactions = listOf(
            Transaction(
                title = "To: Electricity company",
                amount = "-£20,00",
                description = "Upcoming • Due on...",
                amountColor = Color.Red
            ),
            Transaction(
                title = "SumUp pay-in",
                amount = "+£98,10",
                description = "15:13 • PID 298584",
                amountColor = Color.Blue
            )
        ),
        incomingOutgoingTitle = "Incoming vs. Outgoing",
        actionButtons = listOf(
            ActionButtonData(icon = Icons.Default.ArrowForward, label = "Send money"),
            ActionButtonData(icon = Icons.Default.Add, label = "Add money"),
            ActionButtonData(icon = Icons.Default.List, label = "Insights")
        )
    )
    BusinessAccountScreen(uiState = sampleUiState) {}
}
