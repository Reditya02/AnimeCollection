package com.example.animecollection.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animecollection.core.UIState
import com.example.animecollection.domain.usecase.GetSearchedAnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchedAnimeUseCase: GetSearchedAnimeUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(SearchState())
    val state: StateFlow<SearchState> = _state

    fun search(query: String) = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }
        getSearchedAnimeUseCase(query).collectLatest { uiState ->
            when(uiState) {
                is UIState.Error -> _state.update { it.copy(errorMessage = uiState.message ?: "Error") }
                is UIState.Loading -> _state.update { it.copy(isLoading = true) }
                is UIState.Success -> {
                    if (uiState.data != null && uiState.data.isNotEmpty()) {
                        _state.update { it.copy(
                            isLoading = false,
                            result = uiState.data
                        ) }
                    } else {
                        _state.update { it.copy(errorMessage = uiState.message ?: "Error") }
                    }
                }
            }
        }
    }

    fun onSearchtextFieldvalueChanged(value: String) {
        _state.update { it.copy(query = value) }
    }
}