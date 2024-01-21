package com.example.animecollection.ui.settings

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animecollection.core.UIState
import com.example.animecollection.domain.usecase.theme.ChangeThemeUseCase
import com.example.animecollection.domain.usecase.theme.GetIsDarkThemeUseCase
import com.example.animecollection.domain.usecase.auth.LogoutUseCase
import com.example.animecollection.domain.usecase.auth.UploadImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
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
    private val _state = MutableStateFlow(SettingsState())
    val state: StateFlow<SettingsState> = _state

    init {
        getIsDarkTheme()
    }

    private fun getIsDarkTheme() = _state.update {
        it.copy( isDarkMode = getIsDarkThemeUseCase() )
    }

    fun changeTheme() {
        changeThemeUseCase()
        getIsDarkTheme()
    }

    fun uploadImage(uri: Uri) = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }
        uploadImageUseCase(uri).collectLatest { uiState ->
            when(uiState) {
                is UIState.Error -> _state.update { it.copy(
                    isUploadSucccess = false,
                    isLoading = false,
                    errorMessage = uiState.message ?: "Error"
                ) }
                is UIState.Loading -> _state.update { it.copy(isLoading = true) }
                is UIState.Success -> _state.update { it.copy(
                    isLoading = false,
                    isUploadSucccess = true
                ) }
            }
            delay(100)
            _state.update { it.copy(errorMessage = "") }
        }
    }

    fun logout() = logoutUseCase()
}