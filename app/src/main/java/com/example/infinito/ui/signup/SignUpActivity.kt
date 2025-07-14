package com.example.infinito.ui.signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener
import com.example.infinito.R
import com.example.infinito.ui.home.HomeActivity
import com.example.infinito.ui.login.LoginActivity
import com.example.infinito.utils.md5.toMD5
import com.example.infinito.utils.theme.setFixedTheme
import com.google.android.material.textfield.TextInputEditText
import java.security.MessageDigest

class SignUpActivity : AppCompatActivity() {

    private lateinit var loginTextBtn: TextView
    private lateinit var nameInput: TextInputEditText
    private lateinit var surnameInput: TextInputEditText
    private lateinit var emailInput: TextInputEditText
    private lateinit var passwordInput: TextInputEditText
    private lateinit var confirmPasswordInput: TextInputEditText
    private lateinit var registerBtn: Button

    private lateinit var nameError: TextView
    private lateinit var surnameError: TextView
    private lateinit var emailError: TextView
    private lateinit var passwordError: TextView
    private lateinit var confirmPasswordError: TextView

    private var name: String = ""
    private var surname: String = ""
    private var email: String = ""
    private var password: String = ""
    private var confirmPassword: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        setFixedTheme(this, window)

        loginTextBtn = findViewById(R.id.loginTextBtn)
        nameInput = findViewById(R.id.nameInput)
        surnameInput = findViewById(R.id.surnameInput)
        emailInput = findViewById(R.id.emailInput)
        passwordInput = findViewById(R.id.passwordInput)
        confirmPasswordInput = findViewById(R.id.confirmPasswordInput)
        registerBtn = findViewById(R.id.registerBtn)

        nameError = findViewById(R.id.nameError)
        surnameError = findViewById(R.id.surnameError)
        emailError = findViewById(R.id.emailError)
        passwordError = findViewById(R.id.passwordError)
        confirmPasswordError = findViewById(R.id.confirmPasswordError)

        val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

        nameInput.addTextChangedListener {
            if (nameInput.text.toString().isNotEmpty()) {
                nameError.visibility = View.GONE
                name = nameInput.text.toString()
            } else {
                nameError.visibility = View.VISIBLE
            }
        }

        surnameInput.addTextChangedListener {
            if (surnameInput.text.toString().isNotEmpty()) {
                surnameError.visibility = View.GONE
                surname = surnameInput.text.toString()
            } else {
                surnameError.visibility = View.VISIBLE
            }
        }

        emailInput.addTextChangedListener {
            val emailRegex = Regex(
                "[a-zA-Z0-9+._%\\-]+@[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,}"
            )
            if (emailRegex.matches(emailInput.text.toString())) {
                emailError.visibility = View.GONE
                email = emailInput.text.toString()
            } else {
                emailError.visibility = View.VISIBLE
            }
        }

        passwordInput.addTextChangedListener {
            val passwordRegex = Regex(
                "^(?=.*[A-Z])(?=.*[!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?])(?=(?:.*\\d){2,}).{8,}\$"
            )
            if (passwordRegex.matches(passwordInput.text.toString())) {
                passwordError.visibility = View.GONE
                password = passwordInput.text.toString()
            } else {
                passwordError.visibility = View.VISIBLE
            }
        }

        confirmPasswordInput.addTextChangedListener {
            if (passwordInput.text.toString() == confirmPasswordInput.text.toString()) {
                confirmPasswordError.visibility = View.GONE
                confirmPassword = confirmPasswordInput.text.toString()
            } else {
                confirmPasswordError.visibility = View.VISIBLE
            }
        }

        registerBtn.setOnClickListener {
            if (name.isNotEmpty() && surname.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                sharedPref.edit {
                    putString("name", name)
                    putString("surname", surname)
                    putString("email", email)
                    putString("password", password.toMD5())
                    apply()
                }
                Toast.makeText(this, "Registrazione Completata!", Toast.LENGTH_LONG).show()
                val homeIntent = Intent(this, HomeActivity::class.java)
                startActivity(homeIntent)
                finish()
            }
        }

        loginTextBtn.setOnClickListener {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }
    }
}