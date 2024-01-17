package com.example.animecollection.domain.usecase.favorite

import com.example.animecollection.domain.repository.IUserRepository
import javax.inject.Inject

class CheckIsFavoriteUseCase @Inject constructor(
    private val repository: IUserRepository
) {
    operator fun invoke(id: String) =
        repository.checkIsFavorite(id)
}