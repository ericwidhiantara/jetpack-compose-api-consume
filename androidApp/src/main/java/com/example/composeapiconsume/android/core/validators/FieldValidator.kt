package com.example.composeapiconsume.android.core.validators


interface FieldValidator<T> {
    fun validate(value: T): Boolean
}