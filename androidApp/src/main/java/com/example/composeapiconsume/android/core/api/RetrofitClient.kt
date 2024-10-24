package com.example.composeapiconsume.android.core.api

import com.example.composeapiconsume.android.core.error.Failure
import com.example.composeapiconsume.android.core.error.ServerFailure
import com.example.composeapiconsume.android.core.error.UnauthorizedFailure
import com.example.composeapiconsume.android.utils.helper.Either
import com.example.composeapiconsume.android.utils.helper.Left
import com.example.composeapiconsume.android.utils.helper.Right
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.QueryMap
import java.util.concurrent.TimeUnit

interface ApiService {
    @GET("{url}")
    suspend fun getRequest(
        @Path("url") url: String,
        @QueryMap queryParams: Map<String, String>? = null
    ): ApiResponse

    @POST("{url}")
    suspend fun postRequest(
        @Path("url") url: String,
        @Body body: Any? = null
    ): ApiResponse

    @PATCH("{url}")
    suspend fun patchRequest(
        @Path("url") url: String,
        @Body body: Any? = null
    ): ApiResponse

    @PUT("{url}")
    suspend fun putRequest(
        @Path("url") url: String,
        @Body body: Any? = null
    ): ApiResponse

    @DELETE("{url}")
    suspend fun deleteRequest(
        @Path("url") url: String,
        @Body body: Any? = null
    ): ApiResponse
}

class RetrofitClient(token: String?) {

    private val baseUrl = ListAPI.BASE_URL

    // OkHttpClient with interceptor for logging and error handling
    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(RetrofitInterceptor())  // <-- Injecting the custom interceptor
            .addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                // Adding Authorization token if available
                token?.let {
                    requestBuilder.header("Authorization", "Bearer $it")
                }
                chain.proceed(requestBuilder.build())
            }
            .build()
    }

    // Retrofit instance
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // com.example.composeapiconsume.android.core.api.ApiService instance
    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    // Unified request handler with error mapping
    suspend fun <T> makeRequest(
        call: suspend () -> T
    ): Either<Failure, T> {
        return try {
            // Successful API call
            Right(call())
        } catch (e: Exception) {
            // Handle various error scenarios
            when (e) {
                is retrofit2.HttpException -> {
                    val code = e.code()
                    when (code) {
                        401 -> Left(UnauthorizedFailure("Unauthorized"))
                        503 -> Left(ServerFailure("Service Unavailable"))
                        else -> Left(ServerFailure("Error: ${e.message()}"))
                    }
                }

                is java.net.SocketTimeoutException -> {
                    Left(ServerFailure("Connection timeout, please check your internet connection"))
                }

                else -> Left(ServerFailure("Cannot connect to server: ${e.localizedMessage}"))
            }
        }
    }
}

// Example response model
data class ApiResponse(
    val status: Int,
    val message: String,
    val data: Any?
)

