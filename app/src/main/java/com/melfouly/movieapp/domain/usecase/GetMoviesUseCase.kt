package com.melfouly.movieapp.domain.usecase

import com.melfouly.movieapp.domain.repo.MoviesRepo

class GetMoviesUseCase(private val movieRepo: MoviesRepo) {

    suspend operator fun invoke(page: Int) = movieRepo.getMovies(page)
}