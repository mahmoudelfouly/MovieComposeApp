package com.melfouly.movieapp.domain.usecase

import com.melfouly.movieapp.domain.repo.SeriesRepo

class GetSeriesUseCase(private val repo: SeriesRepo) {

    suspend operator fun invoke(page: Int) = repo.getSeries(page)
}