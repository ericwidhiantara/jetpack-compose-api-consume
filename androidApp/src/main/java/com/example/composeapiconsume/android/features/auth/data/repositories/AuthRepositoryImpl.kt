package com.example.composeapiconsume.android.features.auth.data.repositories

import com.example.composeapiconsume.android.core.error.Failure
import com.example.composeapiconsume.android.features.auth.data.datasources.AuthRemoteDataSource
import com.example.composeapiconsume.android.features.auth.data.models.LoginResponse
import com.example.composeapiconsume.android.features.auth.domain.repositories.AuthRepository
import com.example.composeapiconsume.android.utils.helper.Either
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: AuthRemoteDataSource
) : AuthRepository {

    override suspend fun login(params: Map<String, String>): Either<Failure, LoginResponse> {
        return remoteDataSource.login(params)
    }
}
