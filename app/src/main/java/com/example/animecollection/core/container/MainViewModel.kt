package com.example.animecollection.core.container

import androidx.lifecycle.ViewModel
import com.example.animecollection.domain.usecase.theme.GetIsDarkThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getIsDarkThemeUseCase: GetIsDarkThemeUseCase
) : ViewModel() {
    fun getIsDarkTheme() = getIsDarkThemeUseCase()
}