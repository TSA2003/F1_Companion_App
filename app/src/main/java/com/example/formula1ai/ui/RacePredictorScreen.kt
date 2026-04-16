package com.example.formula1ai.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.formula1ai.viewmodel.RacePredictorViewModel

@Composable
fun RacePredictorScreen(viewModel: RacePredictorViewModel = viewModel()) {

    var raceName by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "🏁 Race Predictor",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        TextField(
            value = raceName,
            onValueChange = { raceName = it },
            label = { Text("Enter race (e.g. Monaco GP)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                viewModel.predictRace(raceName)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Predict")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Show loader while fetching
        if (viewModel.isLoading.value) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Only show the container if there is actual text to display
        if (viewModel.result.value.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // Takes up remaining space
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .verticalScroll(scrollState)
                    .padding(12.dp)
            ) {
                Text(
                    text = viewModel.result.value,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}