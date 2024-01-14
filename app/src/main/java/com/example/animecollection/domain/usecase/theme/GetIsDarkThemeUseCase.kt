package com.example.animecollection.domain.usecase.theme

import com.example.animecollection.domain.repository.ILocaleRepository
import javax.inject.Inject

class GetIsDarkThemeUseCase @Inject constructor(
    private val repository: ILocaleRepository
) {
    operator fun invoke() = repository.getIsDarkTheme()
}