package com.example.infinito.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.infinito.R
import com.example.infinito.ui.signup.SignUpActivity
import com.example.infinito.utils.theme.setFixedTheme

class LoginActivity : AppCompatActivity() {

    private lateinit var registerTextBtn: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        setFixedTheme(this, window)

        registerTextBtn = findViewById(R.id.registerTextBtn)

        registerTextBtn.setOnClickListener {
            val registerIntent = Intent(this, SignUpActivity::class.java)
            startActivity(registerIntent)
        }
    }
}