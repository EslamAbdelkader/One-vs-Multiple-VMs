// MainActivity.kt
package com.example.myandroidplayground.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.myandroidplayground.presentation.BusinessAccountViewModel
import com.example.myandroidplayground.ui.compose.BusinessAccountScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val businessAccountViewModel: BusinessAccountViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isLoading by businessAccountViewModel.isLoading.collectAsState()
            val uiState by businessAccountViewModel.uiState.collectAsState()
            val errorMessage by businessAccountViewModel.errorMessage.collectAsState()

            Box(modifier = Modifier.fillMaxSize()) {
                uiState?.let { state ->
                    BusinessAccountScreen(
                        uiState = state,
                        onRefresh = { businessAccountViewModel.refreshData() }
                    )
                }
                if (isLoading) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                errorMessage?.let { error ->
                    Toast.makeText(applicationContext, error, Toast.LENGTH_SHORT).show()
                    businessAccountViewModel.onErrorHandled()
                }
            }
        }
    }
}
