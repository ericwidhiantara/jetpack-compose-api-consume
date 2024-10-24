package com.example.composeapiconsume.android.core.di

import com.example.composeapiconsume.android.features.auth.data.datasources.AuthRemoteDataSource
import com.example.composeapiconsume.android.features.auth.data.datasources.AuthRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun bindAuthRemoteDataSource(
        authRemoteDataSourceImpl: AuthRemoteDataSourceImpl
    ): AuthRemoteDataSource
}