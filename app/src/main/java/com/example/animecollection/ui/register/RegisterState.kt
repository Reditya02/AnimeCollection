package com.example.animecollection.ui.register

data class RegisterState(
    val isRegister: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String = "",

    val name: String = "",
    val email: String = "",
    val password: String = "",
    val retypePassword: String = "",

    val isEmailValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val isRetypePasswordValid: Boolean = false,
    val isNameValid: Boolean = false,
    val isRegisterValid: Boolean = false,

    val isNotEmpty: Boolean = false,

    val isPasswordShown: Boolean = false,
    val isRetypePasswordShown: Boolean = false

)
