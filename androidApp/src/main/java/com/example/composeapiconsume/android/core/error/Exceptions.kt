package com.example.composeapiconsume.android.core.error

class ServerException(override val message: String?) : Exception(message)

class CacheException : Exception()
