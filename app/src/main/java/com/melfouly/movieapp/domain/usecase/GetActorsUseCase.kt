package com.melfouly.movieapp.domain.usecase

import com.melfouly.movieapp.domain.repo.ActorsRepo

class GetActorsUseCase(private val repo: ActorsRepo) {

    suspend operator fun invoke(page: Int) = repo.getActors(page)
}