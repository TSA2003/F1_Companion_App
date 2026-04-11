package com.example.formula1ai.data

import com.example.formula1ai.BuildConfig
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class GeminiService {

    private val client = OkHttpClient()

    // ⚠️ Премести това в local.properties за production
    private val apiKey = BuildConfig.GEMINI_API_KEY

    fun sendMessage(prompt: String, callback: (String) -> Unit) {

        try {
            // 🧠 Създаваме JSON заявка
            val part = JSONObject().apply {
                put("text", prompt)
            }

            val partsArray = JSONArray().apply {
                put(part)
            }

            val content = JSONObject().apply {
                put("parts", partsArray)
            }

            val contentsArray = JSONArray().apply {
                put(content)
            }

            val json = JSONObject().apply {
                put("contents", contentsArray)
            }

            val body = json.toString()
                .toRequestBody("application/json".toMediaType())

            val request = Request.Builder()
                .url("https://generativelanguage.googleapis.com/v1beta/models/gemini-flash-latest:generateContent")
                .header("X-goog-api-key", apiKey)
                .post(body)
                .build()

            client.newCall(request).enqueue(object : Callback {

                override fun onFailure(call: Call, e: IOException) {
                    callback("Error: ${e.message}")
                }

                override fun onResponse(call: Call, response: Response) {

                    val responseBody = response.body?.string()

                    if (responseBody.isNullOrEmpty()) {
                        callback("Empty response")
                        return
                    }

                    try {
                        val jsonResponse = JSONObject(responseBody)

                        val text = jsonResponse
                            .getJSONArray("candidates")
                            .getJSONObject(0)
                            .getJSONObject("content")
                            .getJSONArray("parts")
                            .getJSONObject(0)
                            .getString("text")

                        callback(text)

                    } catch (e: Exception) {
                        callback("Parsing error: ${e.message}")
                    }
                }
            })

        } catch (e: Exception) {
            callback("Request error: ${e.message}")
        }
    }
}