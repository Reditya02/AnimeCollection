package com.example.animecollection.ui.trending

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animecollection.core.UIState
import com.example.animecollection.domain.usecase.GetAllAnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingViewModel @Inject constructor(
    private val getAllAnimeUseCase: GetAllAnimeUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(TrendingState())
    val state: StateFlow<TrendingState> = _state

    init {
        _state.update { it.copy(isLoading = true) }
        getAllAnime()
    }

    private fun getAllAnime() = viewModelScope.launch {
        getAllAnimeUseCase().collectLatest { uiState ->
            when(uiState) {
                is UIState.Error -> _state.update { it.copy(message = uiState.message ?: "Error") }
                is UIState.Loading -> _state.update { it.copy(isLoading = true) }
                is UIState.Success -> {
                    if (uiState.data != null && uiState.data.isNotEmpty()) {
                        _state.update { it.copy(
                            isLoading = false,
                            listAnime = uiState.data
                        ) }
                    } else {
                        _state.update { it.copy(message = uiState.message ?: "Error") }
                    }
                }
            }
        }
    }
}