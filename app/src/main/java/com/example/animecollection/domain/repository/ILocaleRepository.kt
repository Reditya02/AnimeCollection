package com.example.animecollection.domain.repository

interface ILocaleRepository {
    fun changeTheme()
    fun getIsDarkTheme() : Boolean
}