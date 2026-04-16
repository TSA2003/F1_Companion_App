package com.example.formula1ai.viewmodel

import com.example.formula1ai.data.GeminiService
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class RacePredictorViewModel : ViewModel() {

    private val gemini = GeminiService()

    var result = mutableStateOf("")
        private set

    var isLoading = mutableStateOf(false)
        private set

    fun predictRace(raceName: String) {

        isLoading.value = true

        val prompt = """
            You are a Formula 1 expert analyst.
            
            If you don't know the answer, search in Internet.

            Predict the TOP 3 finishers for the race: $raceName.

            Provide:
            1. Driver name - Team
            2. Driver name - Team
            3. Driver name - Team

            Also include short reasoning based on:
            - preseason testing
            - results from the previous races of the given season
            - if the asked race is first for the season, look for the results of the previous season
            - driver form
            - team performance
            - track characteristics

            Keep it concise and structured.
        """.trimIndent()

        gemini.sendMessage(prompt) { response ->
            result.value = response
            isLoading.value = false
        }
    }
}