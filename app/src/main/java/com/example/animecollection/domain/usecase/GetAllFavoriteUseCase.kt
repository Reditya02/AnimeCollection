package com.example.animecollection.domain.usecase

import com.example.animecollection.domain.repository.IUserRepository
import javax.inject.Inject

class GetAllFavoriteUseCase @Inject constructor(
    private val repository: IUserRepository
) {
    operator fun invoke() =
        repository.getAllFavorite()
}