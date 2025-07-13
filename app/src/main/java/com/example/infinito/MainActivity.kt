package com.example.infinito

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.infinito.ui.start.StartActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startIntent = Intent(this, StartActivity::class.java)
        startActivity(startIntent)
        finish()
    }
}