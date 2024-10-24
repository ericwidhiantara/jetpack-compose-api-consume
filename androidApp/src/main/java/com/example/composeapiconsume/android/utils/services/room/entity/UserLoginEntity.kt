package com.example.composeapiconsume.android.utils.services.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_login")
data class UserLoginEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val token: String,
    val fcm: String,
    val language: String,
    val theme: String,
    val locale: String,
    val isLogin: Boolean,
    val tokenData: String
)
