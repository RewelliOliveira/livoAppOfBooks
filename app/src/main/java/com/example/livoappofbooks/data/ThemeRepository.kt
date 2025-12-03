package com.example.livoappofbooks.data

class ThemeRepository(private val prefs: ThemePreferences) {

    val isDarkTheme = prefs.isDarkTheme

    suspend fun setDarkTheme(enabled: Boolean) {
        prefs.setDarkTheme(enabled)
    }
}
