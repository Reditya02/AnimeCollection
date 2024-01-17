package com.example.animecollection.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animecollection.core.UIState
import com.example.animecollection.domain.model.Anime
import com.example.animecollection.domain.usecase.AddFavoriteUseCase
import com.example.animecollection.domain.usecase.GetAnimeCharacterUseCase
import com.example.animecollection.domain.usecase.GetGenreUseCase
import com.example.animecollection.ui.detail.model.AnimeCharacterState
import com.example.animecollection.ui.detail.model.GenreState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getAnimeCharacterUseCase: GetAnimeCharacterUseCase,
    private val getGenreUseCase: GetGenreUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase
) : ViewModel() {
    private val _characterState = MutableStateFlow(AnimeCharacterState())
    val characterState: StateFlow<AnimeCharacterState> = _characterState

    private val _genreState = MutableStateFlow(GenreState())
    val genreState: StateFlow<GenreState> = _genreState

    fun getGenre(id: String) = viewModelScope.launch {
        getGenreUseCase(id).collectLatest { uiState ->
            when(uiState) {
                is UIState.Error -> _genreState.update { it.copy(
                    isLoading = false,
                    message = uiState.message ?: "Error"
                ) }
                is UIState.Loading -> _genreState.update { it.copy(isLoading = true) }
                is UIState.Success -> {
                    if (uiState.data != null) {
                        _genreState.update { it.copy(
                            isLoading = false,
                            genre = uiState.data
                        ) }
                    } else {
                        _genreState.update { it.copy(
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

    fun addFavorite(anime: Anime) = addFavoriteUseCase(anime)
}