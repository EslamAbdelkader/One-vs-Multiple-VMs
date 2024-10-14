package com.example.myandroidplayground.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myandroidplayground.R
import com.example.myandroidplayground.presentation.UiState

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
