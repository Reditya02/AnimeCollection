package com.example.animecollection.ui.settings

data class SettingsState(
    val isDarkMode: Boolean = false,
    val isUploadSucccess: Boolean = false,
    val errorMessage: String = "",
    val isLoading: Boolean = false
)
