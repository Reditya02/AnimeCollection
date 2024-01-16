package com.example.animecollection.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animecollection.core.UIState
import com.example.animecollection.domain.usecase.GetAnimeCharacterUseCase
import com.example.animecollection.domain.usecase.GetDetailAnimeUseCase
import com.example.animecollection.ui.detail.model.AnimeCharacterState
import com.example.animecollection.ui.detail.model.DetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getDetailAnimeUseCase: GetDetailAnimeUseCase,
    private val getAnimeCharacterUseCase: GetAnimeCharacterUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(DetailState())
    val state: StateFlow<DetailState> = _state

    private val _characterState = MutableStateFlow(AnimeCharacterState())
    val characterState: StateFlow<AnimeCharacterState> = _characterState

    fun getDetailAnime(id: String) = viewModelScope.launch {
        getDetailAnimeUseCase(id).collectLatest { uiState ->
            when(uiState) {
                is UIState.Error -> _state.update { it.copy(
                    isLoading = false,
                    message = uiState.message ?: "Error"
                ) }
                is UIState.Loading -> _state.update { it.copy(isLoading = true) }
                is UIState.Success -> {
                    if (uiState.data != null) {
                        getAnimeCharacter(id)
                        _state.update { it.copy(
                            isLoading = false,
                            anime = uiState.data
                        ) }
                    } else {
                        _state.update { it.copy(
                            isLoading = false,
                            message = uiState.message ?: "Error"
                        ) }
                    }
                }
            }
        }
    }

    fun getAnimeCharacter(id: String) = viewModelScope.launch {
        getAnimeCharacterUseCase(id).collectLatest { uiState ->
            when(uiState) {
                is UIState.Error -> _characterState.update { it.copy(
                    isLoading = false,
                    message = uiState.message ?: "Error"
                ) }
                is UIState.Loading -> _characterState.update { it.copy(isLoading = true) }
                is UIState.Success -> {
                    if (uiState.data != null) {
                        _characterState.update { it.copy(
                            isLoading = false,
                            character = uiState.data
                        ) }
                    } else {
                        _characterState.update { it.copy(
                            isLoading = false,
                            message = uiState.message ?: "Error"
                        ) }
                    }
                }
            }
        }
    }
}