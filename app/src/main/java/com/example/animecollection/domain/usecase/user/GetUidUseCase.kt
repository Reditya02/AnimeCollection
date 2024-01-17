package com.example.animecollection.domain.usecase.user

import com.example.animecollection.domain.repository.IUserRepository
import javax.inject.Inject

class GetUidUseCase @Inject constructor(
    private val repository: IUserRepository
) {
    operator fun invoke(): String =
        repository.getUid()
}