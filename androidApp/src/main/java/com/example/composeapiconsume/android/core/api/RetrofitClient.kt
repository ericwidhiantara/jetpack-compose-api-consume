package com.example.composeapiconsume.android.core.api

import com.example.composeapiconsume.android.core.error.Failure
import com.example.composeapiconsume.android.core.error.ServerFailure
import com.example.composeapiconsume.android.core.error.UnauthorizedFailure
import com.example.composeapiconsume.android.utils.helper.Either
import com.example.composeapiconsume.android.utils.services.room.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient(token: String?) {

    private val baseUrl = ListAPI.BASE_URL

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(RetrofitInterceptor())
            .addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                token?.let {
                    requestBuilder.header("Authorization", "Bearer $it")
                }
                chain.proceed(requestBuilder.build())
            }
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    suspend fun <T> makeRequest(
        call: suspend () -> T
    ): Either<Failure, T> {
        return try {
            Either.Right(call()) // Call should return LoginResponse
        } catch (e: Exception) {
            when (e) {
                is retrofit2.HttpException -> {
                    // Handle HTTP exceptions, potentially parsing error body
                    val code = e.code()
                    when (code) {
                        401 -> Either.Left(UnauthorizedFailure("Unauthorized"))
                        503 -> Either.Left(ServerFailure("Service Unavailable"))
                        else -> Either.Left(ServerFailure("Error: ${e.message()}"))
                    }
                }

                is java.net.SocketTimeoutException -> {
                    Either.Left(ServerFailure("Connection timeout, please check your internet connection"))
                }

                else -> Either.Left(ServerFailure("Cannot connect to server: ${e.localizedMessage}"))
            }
        }
    }

}
