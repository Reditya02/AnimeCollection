package com.example.animecollection.domain.usecase.auth

import com.example.animecollection.domain.repository.IUserRepository
import javax.inject.Inject

class GetUserDataUseCase @Inject constructor(
    private val repository: IUserRepository
) {
    operator fun invoke() =
        repository.getUserData()
}