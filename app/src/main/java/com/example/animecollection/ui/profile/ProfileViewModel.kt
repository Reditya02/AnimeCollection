package com.example.animecollection.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animecollection.core.UIState
import com.example.animecollection.domain.usecase.auth.GetUserDataUseCase
import com.example.animecollection.domain.usecase.favorite.GetAllFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val getAllFavoriteUseCase: GetAllFavoriteUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state

    init {
        getUserData()
        getListFavorite()
    }

    private fun getListFavorite() = viewModelScope.launch {
        getAllFavoriteUseCase(null).collectLatest { uiState ->
            when(uiState) {
                is UIState.Error -> {}
                is UIState.Loading -> {}
                is UIState.Success -> {
                    if (uiState.data != null) {
                        _state.update { it.copy(
                            listFavorite = uiState.data
                        ) }
                    }
                }
            }
        }
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
                    }
                }
            }
        }
    }
}