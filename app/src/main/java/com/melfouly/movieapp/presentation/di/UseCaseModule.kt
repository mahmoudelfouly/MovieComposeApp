package com.melfouly.movieapp.presentation.di

import com.melfouly.movieapp.domain.repo.ActorsRepo
import com.melfouly.movieapp.domain.repo.MoviesRepo
import com.melfouly.movieapp.domain.repo.SeriesRepo
import com.melfouly.movieapp.domain.usecase.GetActorDetailsUseCase
import com.melfouly.movieapp.domain.usecase.GetActorsUseCase
import com.melfouly.movieapp.domain.usecase.GetMovieDetailsUseCase
import com.melfouly.movieapp.domain.usecase.GetMoviesUseCase
import com.melfouly.movieapp.domain.usecase.GetSeriesDetailsUseCase
import com.melfouly.movieapp.domain.usecase.GetSeriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    /**
     * Movies Use Cases
     */
    @Singleton
    @Provides
    fun provideMoviesUseCase(repo: MoviesRepo): GetMoviesUseCase = GetMoviesUseCase(repo)

    @Singleton
    @Provides
    fun provideMovieDetailsUseCase(repo: MoviesRepo): GetMovieDetailsUseCase =
        GetMovieDetailsUseCase(repo)

    /**
     * Series Use Cases
     */

    @Singleton
    @Provides
    fun provideSeriesUseCase(repo: SeriesRepo): GetSeriesUseCase = GetSeriesUseCase(repo)

    @Singleton
    @Provides
    fun provideSeriesDetailsUseCase(repo: SeriesRepo): GetSeriesDetailsUseCase =
        GetSeriesDetailsUseCase(repo)

    /**
     * Actors Use Cases
     */

    @Singleton
    @Provides
    fun provideActorsUseCase(repo: ActorsRepo): GetActorsUseCase = GetActorsUseCase(repo)

    @Singleton
    @Provides
    fun provideActorDetailsUseCase(repo: ActorsRepo): GetActorDetailsUseCase =
        GetActorDetailsUseCase(repo)
}