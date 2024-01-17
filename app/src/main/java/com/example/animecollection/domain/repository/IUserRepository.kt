package com.example.animecollection.domain.repository

import com.example.animecollection.core.UIState
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    fun login(email: String, password: String): Flow<UIState<Boolean>>
    fun register(username: String, email: String, password: String): Flow<UIState<Boolean>>
    fun logout()
}