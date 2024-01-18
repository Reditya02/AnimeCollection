package com.example.animecollection.ui.settings

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animecollection.domain.usecase.theme.ChangeThemeUseCase
import com.example.animecollection.domain.usecase.theme.GetIsDarkThemeUseCase
import com.example.animecollection.domain.usecase.auth.LogoutUseCase
import com.example.animecollection.domain.usecase.auth.UploadImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getIsDarkThemeUseCase: GetIsDarkThemeUseCase,
    private val changeThemeUseCase: ChangeThemeUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val uploadImageUseCase: UploadImageUseCase
) : ViewModel() {
    private val _isDarkModeState = MutableStateFlow(false)
    val isDarkModeState: StateFlow<Boolean> = _isDarkModeState

    init {
        getIsDarkTheme()
    }

    private fun getIsDarkTheme() = _isDarkModeState.update { getIsDarkThemeUseCase() }

    fun changeTheme() {
        changeThemeUseCase()
        getIsDarkTheme()
    }

    fun uploadImage(uri: Uri) = viewModelScope.launch { uploadImageUseCase(uri) }

    fun logout() = logoutUseCase()
}