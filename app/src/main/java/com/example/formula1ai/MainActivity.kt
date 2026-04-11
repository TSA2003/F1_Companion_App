package com.example.formula1ai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.formula1ai.ui.ChatScreen
import com.example.formula1ai.ui.RacePredictorScreen
import com.example.formula1ai.ui.theme.F1Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            F1Theme {

                var currentScreen by remember { mutableStateOf("chat") }

                Column(modifier = Modifier.fillMaxSize()) {

                    // 📌 Content
                    Box(modifier = Modifier.weight(1f)) {
                        when (currentScreen) {

                            "chat" -> ChatScreen()

                            "predictor" -> RacePredictorScreen()
                        }
                    }

                    // 📌 Bottom navigation
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {

                        Button(onClick = { currentScreen = "chat" }) {
                            Text("Chat")
                        }

                        Button(onClick = { currentScreen = "predictor" }) {
                            Text("Predictor")
                        }
                    }
                }
            }
        }
    }
}