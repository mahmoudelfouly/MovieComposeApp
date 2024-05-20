package com.melfouly.movieapp.domain.usecase

import com.melfouly.movieapp.domain.repo.ActorsRepo

class GetActorDetailsUseCase(private val repo: ActorsRepo) {

    suspend operator fun invoke(id: Long) = repo.getActorDetails(id)
}