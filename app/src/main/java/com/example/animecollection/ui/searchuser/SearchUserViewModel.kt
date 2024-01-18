package com.example.animecollection.ui.searchuser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animecollection.core.UIState
import com.example.animecollection.domain.model.User
import com.example.animecollection.domain.usecase.SearchUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchUserViewModel @Inject constructor(
    private val searchUserUseCase: SearchUserUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(SearchUserState())
    val state: StateFlow<SearchUserState> = _state

    fun search(query: String) = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }
        searchUserUseCase(query).collectLatest { uiState ->
            when(uiState) {
                is UIState.Error -> _state.update { it.copy(errorMessage = uiState.message ?: "Error") }
                is UIState.Loading -> _state.update { it.copy(isLoading = true) }
                is UIState.Success -> {
                    if (uiState.data != null && uiState.data.isNotEmpty()) {
                        _state.update { it.copy(
                            isLoading = false,
                            result = uiState.data as List<User>
                        ) }
                    } else {
                        _state.update { it.copy(errorMessage = uiState.message ?: "Error") }
                    }
                }
            }
        }
    }

    fun onSearchTextFieldValueChanged(value: String) {
        _state.update { it.copy(query = value) }
    }
}