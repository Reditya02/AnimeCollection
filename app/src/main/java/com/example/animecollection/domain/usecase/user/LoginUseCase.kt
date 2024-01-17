package com.example.animecollection.domain.usecase.user

import com.example.animecollection.core.UIState
import com.example.animecollection.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: IUserRepository
) {
    operator fun invoke(email: String, password: String): Flow<UIState<Boolean>> =
        repository.login(email, password)
}