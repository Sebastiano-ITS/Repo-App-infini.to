package com.example.infinito.ui.start

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.infinito.R
import com.example.infinito.ui.home.HomeActivity
import com.example.infinito.utils.theme.setFixedTheme

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_start)
        setFixedTheme(this, window)

        val earthGif = findViewById<ImageView>(R.id.earthGif)
        Glide.with(this)
            .asGif()
            .load(R.drawable.earth)
            .into(earthGif)

        val startButton = findViewById<Button>(R.id.startButton)
        startButton.setOnClickListener {
            val homeIntent = Intent(this, HomeActivity::class.java)
            startActivity(homeIntent)
            finish()
        }
    }
}