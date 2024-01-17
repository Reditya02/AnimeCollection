package com.example.animecollection.domain.repository

import com.example.animecollection.core.UIState
import com.example.animecollection.domain.model.Anime
import com.example.animecollection.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    fun login(email: String, password: String): Flow<UIState<Boolean>>
    fun register(username: String, email: String, password: String): Flow<UIState<Boolean>>
    fun getUserData(): Flow<UIState<User?>>
    fun getUid(): String
    fun logout()

    fun getAllFavorite(): Flow<UIState<MutableList<Anime>>>
    fun checkIsFavorite(id: String): Flow<UIState<Boolean>>
    fun addFavorite(anime: Anime)
}