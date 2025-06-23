package com.example.infinito

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.infinito.ui.start.StartActivity
import com.example.infinito.utils.theme.setFixedTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setFixedTheme(this, window)

        val startIntent = Intent(this, StartActivity::class.java)
        startActivity(startIntent)
        finish()
    }
}