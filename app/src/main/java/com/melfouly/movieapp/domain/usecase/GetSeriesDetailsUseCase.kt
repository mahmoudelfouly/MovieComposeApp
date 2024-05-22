package com.melfouly.movieapp.domain.usecase

import com.melfouly.movieapp.domain.repo.SeriesRepo

class GetSeriesDetailsUseCase(private val repo: SeriesRepo) {

    suspend operator fun invoke(id: Long) = repo.getSeriesDetails(id)
}