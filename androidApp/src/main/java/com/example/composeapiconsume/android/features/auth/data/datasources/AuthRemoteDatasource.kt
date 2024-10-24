package com.example.composeapiconsume.android.features.auth.data.datasources

import com.example.composeapiconsume.android.core.api.RetrofitClient
import com.example.composeapiconsume.android.core.error.Failure
import com.example.composeapiconsume.android.features.auth.data.models.LoginForm
import com.example.composeapiconsume.android.features.auth.data.models.LoginResponse
import com.example.composeapiconsume.android.utils.helper.Either
import javax.inject.Inject

interface AuthRemoteDataSource {
    suspend fun login(params: Map<String, String>): Either<Failure, LoginResponse>
}

class AuthRemoteDataSourceImpl @Inject constructor(
    private val api: RetrofitClient
) : AuthRemoteDataSource {

    override suspend fun login(params: Map<String, String>): Either<Failure, LoginResponse> {
        // Prepare the login request using the provided params (username, password)
        val loginRequest = LoginForm(
            username = params["username"] ?: "",
            password = params["password"] ?: ""
        )

        // Call the API and handle the result
        return api.makeRequest {
            api.apiService.login(loginRequest) // This should return LoginResponse
        }
    }
}
