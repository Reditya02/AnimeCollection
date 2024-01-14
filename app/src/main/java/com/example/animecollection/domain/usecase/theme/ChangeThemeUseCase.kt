package com.example.animecollection.domain.usecase.theme

import com.example.animecollection.domain.repository.ILocaleRepository
import javax.inject.Inject

class ChangeThemeUseCase @Inject constructor(
    private val repository: ILocaleRepository
) {
    operator fun invoke() = repository.changeTheme()
}