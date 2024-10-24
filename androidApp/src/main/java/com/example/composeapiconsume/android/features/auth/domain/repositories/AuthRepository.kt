package com.example.composeapiconsume.android.features.auth.domain.repositories

import com.example.composeapiconsume.android.core.error.Failure
import com.example.composeapiconsume.android.features.auth.data.models.LoginResponse
import com.example.composeapiconsume.android.utils.helper.Either

interface AuthRepository {
    suspend fun login(params: Map<String, String>): Either<Failure, LoginResponse>
}
