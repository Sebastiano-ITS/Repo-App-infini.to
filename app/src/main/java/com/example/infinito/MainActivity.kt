package com.example.infinito

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.example.infinito.ui.home.HomeActivity
import com.example.infinito.ui.start.StartActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

        val savedName = sharedPref.getString("name", "")
        val savedSurname = sharedPref.getString("surname", "")
        val savedEmail = sharedPref.getString("email", "")
        val savedPassword = sharedPref.getString("password", "")

        if (savedName!!.isNotEmpty() && savedSurname!!.isNotEmpty() && savedEmail!!.isNotEmpty() && savedPassword!!.isNotEmpty()) {
            val homeIntent = Intent(this, HomeActivity::class.java)
            startActivity(homeIntent)
            finish()
        } else {
            sharedPref.edit {
                putString("name", "")
                putString("surname", "")
                putString("email", "")
                putString("password", "")
                apply()
            }
            val startIntent = Intent(this, StartActivity::class.java)
            startActivity(startIntent)
            finish()
        }
    }
}