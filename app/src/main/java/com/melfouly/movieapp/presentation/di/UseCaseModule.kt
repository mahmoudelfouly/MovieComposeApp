package com.melfouly.movieapp.presentation.di

import com.melfouly.movieapp.domain.repo.MoviesRepo
import com.melfouly.movieapp.domain.usecase.GetMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideMoviesUseCase(repo: MoviesRepo): GetMoviesUseCase {
        return GetMoviesUseCase(repo)
    }
}