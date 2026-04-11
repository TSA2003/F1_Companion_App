package com.example.formula1ai.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.formula1ai.data.GeminiService
import com.example.formula1ai.model.ChatMessage

class ChatViewModel : ViewModel() {

    private val geminiService = GeminiService()

    // State list that Compose observes
    var messages = mutableStateListOf<ChatMessage>()
        private set

    var isLoading by mutableStateOf(false)

    fun sendMessage(userInput: String) {
        if (userInput.isBlank()) return

        // Add user message immediately
        messages.add(ChatMessage(userInput, true))

        val prompt = """
            You are a professional Formula 1 analyst.
            Answer clearly and concisely.

            $userInput
        """.trimIndent()

        isLoading = true

        geminiService.sendMessage(prompt) { response ->
            messages.add(ChatMessage(response, false))
            isLoading = false
        }
    }
}