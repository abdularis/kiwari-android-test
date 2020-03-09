package com.abdularis.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        sessionManager = SessionManager(this)
        if (sessionManager.isLogin) {
            goToChatRoom()
            return
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener { onLoginButtonClick() }
    }

    private fun onLoginButtonClick() {
        val email = etEmail.text.toString()
        if (TextUtils.isEmpty(email)) {
            etEmail.error = "Email tidak boleh kosong"
            return
        }

        val password = etPassword.text.toString()
        if (TextUtils.isEmpty(password)) {
            etPassword.error = "Password tidak boleh kosong"
            return
        }

        checkLoginCredentials(email, password)
    }

    private fun checkLoginCredentials(email: String, password: String) {
        for (userData in USER_DATA_LIST) {
            if (email == userData.email && password == userData.password) {
                sessionManager.isLogin = true
                goToChatRoom()
                return
            }
        }

        Toast.makeText(this, "Login gagal", Toast.LENGTH_SHORT).show()
    }

    private fun goToChatRoom() {
        startActivity(Intent(this, ChatRoomActivity::class.java))
        finish()
    }
}
