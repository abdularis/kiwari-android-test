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
}