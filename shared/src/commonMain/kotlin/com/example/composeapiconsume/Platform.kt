package com.example.composeapiconsume

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform