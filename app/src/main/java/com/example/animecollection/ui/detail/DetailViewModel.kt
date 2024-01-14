package com.example.animecollection.ui.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animecollection.core.UIState
import com.example.animecollection.domain.usecase.GetDetailAnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getDetailAnimeUseCase: GetDetailAnimeUseCase
) : ViewModel() {
    private val _state  = MutableStateFlow(DetailState())
    val state: StateFlow<DetailState> = _state

    init {
        _state.update { it.copy(isLoading = true) }
    }

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
}