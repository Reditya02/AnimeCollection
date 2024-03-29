package com.example.animecollection.ui.guest.splashscreen

import androidx.lifecycle.ViewModel
import com.example.animecollection.domain.usecase.auth.GetUidUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val getUidUseCase: GetUidUseCase
) : ViewModel() {
    fun getUid() = getUidUseCase()
}