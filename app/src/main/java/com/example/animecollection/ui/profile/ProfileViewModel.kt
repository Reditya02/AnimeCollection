package com.example.animecollection.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animecollection.core.UIState
import com.example.animecollection.domain.usecase.auth.GetUserDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state

    init {
        getUserData()
    }

    private fun getUserData() = viewModelScope.launch {
        getUserDataUseCase().collectLatest { uiState ->
            when(uiState) {
                is UIState.Error -> TODO()
                is UIState.Loading -> _state.update { it.copy(isLoading = true) }
                is UIState.Success -> {
                    if (uiState.data != null) {
                        _state.update { it.copy(
                            isLoading = false,
                            data = uiState.data
                        ) }
                    } else {
//                        _state.update { it.copy(errorMessage = uiState.message ?: "Error") }
                    }
                }
            }
        }
    }
}