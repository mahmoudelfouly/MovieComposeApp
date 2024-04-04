package com.melfouly.movieapp.presentation.di

import com.melfouly.movieapp.data.network.ApiService
import com.melfouly.movieapp.data.repo.MoviesRepoImpl
import com.melfouly.movieapp.domain.repo.MoviesRepo
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
    fun provideMoviesRepo(apiService: ApiService): MoviesRepo {
        return MoviesRepoImpl(apiService)
    }
}