package com.melfouly.movieapp.domain.usecase

import com.melfouly.movieapp.domain.repo.MoviesRepo

class GetMovieDetailsUseCase(private val repo: MoviesRepo) {

    suspend operator fun invoke(id: Long) = repo.getMovieDetails(id)
}