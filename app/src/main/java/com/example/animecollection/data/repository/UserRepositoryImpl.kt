package com.example.animecollection.data.repository

import com.example.animecollection.core.UIState
import com.example.animecollection.data.remote.firebase.RemoteFirebaseDatasources
import com.example.animecollection.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val datasource: RemoteFirebaseDatasources
) : IUserRepository {
    override fun login(email: String, password: String): Flow<UIState<Boolean>> =
        datasource.login(email, password)

    override fun register(
        username: String,
        email: String,
        password: String,
    ): Flow<UIState<Boolean>> =
        datasource.register(username, email, password)

    override fun getUid(): String =
        datasource.getUid()

    override fun logout() {
        TODO("Not yet implemented")
    }
}