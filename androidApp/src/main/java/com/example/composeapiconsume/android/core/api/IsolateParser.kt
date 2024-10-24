package com.example.composeapiconsume.android.core.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// Define a converter function type
typealias ResponseConverter<T> = (Map<String, Any?>) -> T

// IsolateParser equivalent in Kotlin
class BackgroundParser<T>(
    private val json: Map<String, Any?>,
    private val converter: ResponseConverter<T>
) {
    // Function to run the parser in the background
    suspend fun parseInBackground(): T {
        return withContext(Dispatchers.IO) {
            // Simulate the conversion, similar to how Dart's Isolate works
            converter(json)
        }
    }
}