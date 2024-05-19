package com.melfouly.movieapp.presentation.di

import com.melfouly.movieapp.data.network.ApiService
import com.melfouly.movieapp.data.repo.ActorsRepoImpl
import com.melfouly.movieapp.data.repo.MoviesRepoImpl
import com.melfouly.movieapp.data.repo.SeriesRepoImpl
import com.melfouly.movieapp.domain.repo.ActorsRepo
import com.melfouly.movieapp.domain.repo.MoviesRepo
import com.melfouly.movieapp.domain.repo.SeriesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMoviesRepo(apiService: ApiService): MoviesRepo = MoviesRepoImpl(apiService)

    @Singleton
    @Provides
    fun provideSeriesRepo(apiService: ApiService): SeriesRepo = SeriesRepoImpl(apiService)

    @Singleton
    @Provides
    fun provideActorsRepo(apiService: ApiService): ActorsRepo = ActorsRepoImpl(apiService)
}