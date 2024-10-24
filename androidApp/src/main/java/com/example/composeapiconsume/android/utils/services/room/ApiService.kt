package com.example.composeapiconsume.android.utils.services.room

import com.example.composeapiconsume.android.features.auth.data.models.LoginForm
import com.example.composeapiconsume.android.features.auth.data.models.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    suspend fun login(
        @Body loginRequest: LoginForm
    ): LoginResponse
}