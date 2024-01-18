package com.example.animecollection.ui.changename

import androidx.lifecycle.ViewModel
import com.example.animecollection.domain.usecase.auth.ChangeNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ChangeNameViewModel @Inject constructor(
    private val changeNameUseCase: ChangeNameUseCase
) : ViewModel() {
    private val _state = MutableStateFlow("")
    val state: StateFlow<String> = _state

    fun changeName(name: String) =
        changeNameUseCase(name)

    fun onTextFieldValueChanged(value: String) =
        _state.update { value }
}