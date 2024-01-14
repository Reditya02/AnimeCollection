package com.example.animecollection.data.locale

import javax.inject.Inject

class LocaleDatasources @Inject constructor(
    private val pref: SharedPref
) {
    fun getTheme() = pref.getTheme()

    fun changeTheme() = pref.changeTheme()
}