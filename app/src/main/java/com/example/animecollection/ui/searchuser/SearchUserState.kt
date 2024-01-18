package com.example.animecollection.ui.searchuser

import com.example.animecollection.domain.model.User

data class SearchUserState(
    val query: String = "",
    val result: List<User> = listOf(),
    val isLoading: Boolean = false,
    val isEmpty: Boolean = false,
    val errorMessage: String = ""
)
