package com.example.animecollection.data.repository

import com.example.animecollection.data.locale.LocaleDatasources
import com.example.animecollection.domain.repository.ILocaleRepository
import javax.inject.Inject

class LocaleRepositoryImpl @Inject constructor(
    private val datasources: LocaleDatasources
) : ILocaleRepository{
    override fun changeTheme() {
        datasources.changeTheme()
    }

    override fun getIsDarkTheme() : Boolean =
        datasources.getTheme()

}