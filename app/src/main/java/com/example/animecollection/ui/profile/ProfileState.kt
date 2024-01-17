package com.example.animecollection.ui.profile

import com.example.animecollection.domain.model.User

data class ProfileState(
    val isLoading: Boolean = true,
    val data: User = User(username = "", email = "", photo = "")
)
