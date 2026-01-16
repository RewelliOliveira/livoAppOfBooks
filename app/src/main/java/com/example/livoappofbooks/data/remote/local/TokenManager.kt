package com.example.livoappofbooks.data.remote.local

import android.content.Context

class TokenManager(context: Context) {
    private val prefs = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        prefs.edit().putString("token", token).apply()
    }

    fun getToken(): String? {
        return prefs.getString("token", null)
    }

    fun saveProfilePath(path: String) {
        prefs.edit().putString("profile_path", path).apply()
    }

    fun getProfilePath(): String? {
        return prefs.getString("profile_path", null)
    }

    fun clearToken() { val editor = prefs.edit()
        editor.remove("token")
        editor.apply()
    }
}