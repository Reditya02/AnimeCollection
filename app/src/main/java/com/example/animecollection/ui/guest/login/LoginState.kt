package com.example.animecollection.ui.guest.login

data class LoginState(
    val isLogin: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String = "",

    val email: String = "",
    val password: String = "",

    val isLoginValid: Boolean = false,

    val isNotEmpty: Boolean = false,

    val isPasswordShown: Boolean = false
)
