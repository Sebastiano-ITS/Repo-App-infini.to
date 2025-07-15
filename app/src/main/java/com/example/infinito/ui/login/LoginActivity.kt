package com.example.infinito.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.infinito.R
import com.example.infinito.ui.home.HomeActivity
import com.example.infinito.ui.signup.SignUpActivity
import com.example.infinito.utils.UserUtils
import com.example.infinito.utils.md5.toMD5
import com.example.infinito.utils.theme.setFixedTheme
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    private lateinit var registerTextBtn: TextView
    private lateinit var emailInput: TextInputEditText
    private lateinit var passwordInput: TextInputEditText
    private lateinit var loginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        setFixedTheme(this, window)

        registerTextBtn = findViewById(R.id.registerTextBtn)

        emailInput = findViewById(R.id.emailInput)
        passwordInput = findViewById(R.id.passwordInput)
        loginBtn = findViewById(R.id.loginBtn)

        val users = UserUtils.getUsers(this)

        loginBtn.setOnClickListener {
            users.map { e ->
                Log.d("email", e.email)
                Log.d("password", e.password)
                if (e.email == emailInput.text.toString() && e.password == passwordInput.text.toString().toMD5()) {
                    e.isLoggedIn = true
                    UserUtils.saveUsers(this, users)
                    Toast.makeText(this, "Login Effettuato!", Toast.LENGTH_LONG).show()
                    val homeIntent = Intent(this, HomeActivity::class.java)
                    startActivity(homeIntent)
                    finish()
                }
            }
            Toast.makeText(this, "Utente non trovato!", Toast.LENGTH_LONG).show()
        }

        registerTextBtn.setOnClickListener {
            val registerIntent = Intent(this, SignUpActivity::class.java)
            startActivity(registerIntent)
            finish()
        }
    }
}