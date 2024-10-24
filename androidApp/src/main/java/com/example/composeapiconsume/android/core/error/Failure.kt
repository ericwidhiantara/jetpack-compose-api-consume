package com.example.composeapiconsume.android.core.error

abstract class Failure// Constructor for Failure
protected constructor(vararg properties: Any?) // Ignoring properties as in Dart
{

    // Optionally, you can add common properties or methods if needed.
}

class ServerFailure(val message: String?) : Failure() {

    override fun equals(other: Any?): Boolean {
        return other is ServerFailure && other.message == message
    }

    override fun hashCode(): Int {
        return message?.hashCode() ?: 0
    }
}

class NoDataFailure : Failure() {

    override fun equals(other: Any?): Boolean {
        return other is NoDataFailure
    }

    override fun hashCode(): Int {
        return 0
    }
}

class CacheFailure : Failure() {

    override fun equals(other: Any?): Boolean {
        return other is CacheFailure
    }

    override fun hashCode(): Int {
        return 0
    }
}

class UnauthorizedFailure(val message: String?) : Failure() {

    override fun equals(other: Any?): Boolean {
        return other is UnauthorizedFailure && other.message == message
    }

    override fun hashCode(): Int {
        return message?.hashCode() ?: 0
    }
}
