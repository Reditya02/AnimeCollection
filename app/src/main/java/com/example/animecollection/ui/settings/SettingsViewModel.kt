package com.example.animecollection.ui.settings

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.example.animecollection.domain.usecase.theme.ChangeThemeUseCase
import com.example.animecollection.domain.usecase.theme.GetIsDarkThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getIsDarkThemeUseCase: GetIsDarkThemeUseCase,
    private val changeThemeUseCase: ChangeThemeUseCase
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
}