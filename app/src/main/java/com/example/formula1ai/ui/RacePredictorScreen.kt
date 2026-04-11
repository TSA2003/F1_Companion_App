package com.example.formula1ai.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.formula1ai.viewmodel.RacePredictorViewModel

@Composable
fun RacePredictorScreen(viewModel: RacePredictorViewModel = viewModel()) {

    var raceName by remember { mutableStateOf("") }

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

        if (viewModel.isLoading.value) {
            CircularProgressIndicator()
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = viewModel.result.value
        )
    }
}