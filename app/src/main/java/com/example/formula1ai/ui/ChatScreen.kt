package com.example.formula1ai.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items // Required for LazyColumn items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel // Required for viewModel()
import com.example.formula1ai.viewmodel.ChatViewModel
import com.example.formula1ai.model.ChatMessage
import com.example.formula1ai.ui.theme.F1Red
import kotlinx.coroutines.launch

@Composable
fun ChatScreen(viewModel: ChatViewModel = viewModel()) {

    var input by remember { mutableStateOf("") }

    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        // 🏁 HEADER
        Text(
            text = "🏎️ F1 AI Companion",
            style = MaterialTheme.typography.headlineSmall,
            color = F1Red,
            modifier = Modifier.padding(16.dp)
        )

        // 💬 CHAT
        LazyColumn(
            modifier = Modifier.weight(1f)
                .fillMaxSize(),
            state = listState,
            contentPadding = PaddingValues(8.dp)
        ) {
            items(viewModel.messages) { message ->
                ChatBubble(message)
            }
        }

        if (viewModel.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(8.dp)
            )
        }

        LaunchedEffect(viewModel.messages.size) {
            if (viewModel.messages.isNotEmpty()) {
                scope.launch {
                    listState.animateScrollToItem(viewModel.messages.lastIndex)
                }
            }
        }

        // ✍️ INPUT BAR
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            TextField(
                value = input,
                onValueChange = { input = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Ask about F1...") }
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    if (input.isNotBlank()) {
                        viewModel.sendMessage(input)
                        input = ""
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = F1Red)
            ) {
                Text("Send")
            }
        }
    }
}

@Composable
fun ChatBubble(message: ChatMessage) {

    val isUser = message.isUser

    val bgColor = if (isUser)
        MaterialTheme.colorScheme.primary
    else
        MaterialTheme.colorScheme.surface

    val alignment = if (isUser)
        Arrangement.End else Arrangement.Start

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = alignment
    ) {

        Card(
            colors = CardDefaults.cardColors(containerColor = bgColor),
            modifier = Modifier
                .padding(6.dp)
                .widthIn(max = 300.dp)
        ) {
            Text(
                text = message.text,
                modifier = Modifier.padding(12.dp),
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}