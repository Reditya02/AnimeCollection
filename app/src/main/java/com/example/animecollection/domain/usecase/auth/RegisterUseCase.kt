package com.example.animecollection.domain.usecase.auth

import com.example.animecollection.core.UIState
import com.example.animecollection.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: IUserRepository
) {
    operator fun invoke(username: String, email: String, password: String): Flow<UIState<Boolean>> =
        repository.register(username, email, password)
}