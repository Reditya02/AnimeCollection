package com.example.animecollection.ui.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animecollection.core.UIState
import com.example.animecollection.domain.usecase.user.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(RegisterState())
    val state: StateFlow<RegisterState> = _state

    fun onNameTextFieldValueChanged(value: String) {
        _state.update { it.copy(name = value) }
        checkEmpty()
    }

    fun onEmailTextFieldValueChanged(value: String) {
        _state.update { it.copy(email = value) }
        checkEmpty()
    }

    fun onPasswordTextFieldValueChanged(value: String) {
        _state.update { it.copy(password = value) }
        checkEmpty()
    }

    fun onRetypePasswordTextFieldValueChanged(value: String) {
        _state.update { it.copy(retypePassword = value) }
        checkEmpty()
    }

    fun onRegisterClicked() {
        viewModelScope.launch {
            isRetypePasswordValid()
            val isValid = _state.value.isRetypePasswordValid
            if (!isValid)
                return@launch
            if (state.value.isNotEmpty)
                register()
        }
    }

    private fun checkEmpty() {
        val isNotEmpty = _state.value.run {
            email.isNotEmpty() && password.isNotEmpty() && retypePassword.isNotEmpty() && name.isNotEmpty()
        }
        _state.update { it.copy(isNotEmpty = isNotEmpty) }
    }

    fun register() = viewModelScope.launch {
        state.value.apply {
            registerUseCase(name, email, password).collectLatest { uiState ->
                Log.d("Reditya", "Register uiState $uiState")
                when(uiState) {
                    is UIState.Error -> _state.update { it.copy(errorMessage = uiState.message ?: "Error") }
                    is UIState.Loading -> _state.update { it.copy(isLoading = true) }
                    is UIState.Success -> _state.update { it.copy(
                        isLoading = false,
                        isRegister = true
                    ) }
                }
            }
        }
    }

    private fun isRetypePasswordValid() {
        _state.update { it.copy(isRetypePasswordValid = it.password == it.retypePassword) }
    }

    fun onHideShowPasswordToggled() {
        _state.update { it.copy(isPasswordShown = !_state.value.isPasswordShown) }
    }

    fun onHideShowRetypePasswordToggled() {
        _state.update { it.copy(isRetypePasswordShown = !_state.value.isRetypePasswordShown) }
    }
}