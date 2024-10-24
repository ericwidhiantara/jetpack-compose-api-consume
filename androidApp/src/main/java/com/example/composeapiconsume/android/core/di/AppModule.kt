package com.example.composeapiconsume.android.core.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
//    //DataSources
//    @Singleton
//    @Provides
//    fun providesMovieRemoteDataSource(
//        api: MovieApi
//    ): MovieRemoteDataSource {
//        return MovieRemoteDataSourceImpl(api)
//    }
//
//    // Repositories
//    @Singleton
//    @Provides
//    fun providesMovieRepository(
//        movieRemoteDataSource: MovieRemoteDataSource
//    ): MovieRepository {
//        return MovieRepositoryImpl(movieRemoteDataSource)
//    }
//
//    // UseCases
//    @Singleton
//    @Provides
//    fun providesGetMoviesUseCase(
//        movieRepository: MovieRepository
//    ): GetMoviesUseCase {
//        return GetMoviesUseCase(movieRepository)
//    }
}