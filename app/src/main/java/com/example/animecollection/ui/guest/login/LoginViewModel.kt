package com.example.animecollection.ui.guest.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animecollection.core.UIState
import com.example.animecollection.domain.usecase.auth.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state

    fun onEmailTextFieldValueChanged(value: String) {
        _state.update { it.copy(email = value) }
        checkEmpty()
    }

    fun onPasswordTextFieldValueChanged(value: String) {
        _state.update { it.copy(password = value) }
        checkEmpty()
    }

    fun onLoginClicked() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            if (state.value.isNotEmpty)
                login()
        }
    }

    private fun login() = viewModelScope.launch {
        state.value.apply {
            loginUseCase(email, password).collectLatest { uiState ->
                when(uiState) {
                    is UIState.Error -> _state.update { it.copy(
                        errorMessage = uiState.message ?: "Error",
                        isLoading = false
                    ) }
                    is UIState.Loading -> _state.update { it.copy(isLoading = true) }
                    is UIState.Success -> _state.update { it.copy(
                        isLoading = false,
                        isLogin = true
                    ) }
                }
                delay(100)
                _state.update { it.copy(errorMessage = "") }
            }
        }
    }

    fun onHideShowPasswordToggled() {
        _state.update { it.copy(isPasswordShown = !_state.value.isPasswordShown) }
    }

    private fun checkEmpty() {
        val isNotEmpty = _state.value.run {
            email.isNotEmpty() && password.isNotEmpty()
        }
        _state.update { it.copy(isNotEmpty = isNotEmpty) }
    }
}