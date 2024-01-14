package com.example.animecollection.data.locale

import android.content.SharedPreferences

class SharedPref(private val preferences: SharedPreferences) {

    fun changeTheme() {
        val theme = getTheme()
        preferences.edit()
            .putBoolean(KEY_THEME, !theme)
            .apply()
    }

    fun getTheme(): Boolean {
        return preferences.getBoolean(KEY_THEME, false)
    }

    companion object {
        private const val KEY_THEME = "key_theme"
    }
}