package com.abdularis.chatapp

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)

    var isLogin: Boolean
        get() = prefs.getBoolean("is_login", false)
        set(value) {
            prefs.edit().putBoolean("is_login", value).apply()
        }

    var name: String
        get() = prefs.getString("name", "") ?: ""
        set(value) {
            prefs.edit().putString("name", value).apply()
        }

    var email: String
        get() = prefs.getString("email", "") ?: ""
        set(value) {
            prefs.edit().putString("email", value).apply()
        }

    var avatar: String
        get() = prefs.getString("avatar", "") ?: ""
        set(value) {
            prefs.edit().putString("avatar", value).apply()
        }
}