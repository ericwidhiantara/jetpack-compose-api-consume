package com.example.composeapiconsume.android.core.di

import com.example.composeapiconsume.android.core.api.ApiService
import com.example.composeapiconsume.android.core.api.RetrofitInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // Singleton scope for the entire app lifecycle
object NetworkModule {

    private const val BASE_URL = "https://your-api-base-url.com/"

    // Provide OkHttpClient with the token interceptor
    @Provides
    @Singleton
    fun provideOkHttpClient(token: String?): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(RetrofitInterceptor())  // Your custom interceptor
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

    // Provide Retrofit instance
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()) // Add Gson converter for parsing JSON
            .build()
    }

    // Provide ApiService
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
