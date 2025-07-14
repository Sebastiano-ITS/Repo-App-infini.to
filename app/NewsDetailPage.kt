package com.example.infinito

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class MainActivity : AppCompatActivity() {
    @SuppressLint("UseKtx")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val discoverButton = findViewById<Button>(R.id.discover_button)
        val scrollView = findViewById<ScrollView>(R.id.scrollView)

        // Apertura link esterno al Planetario
        discoverButton.setOnClickListener {
            val url = "https://www.planetarioditorino.it/italia-nello-spazio"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        // Scroll automatico dopo 1 secondo (facoltativo)
        scrollView.postDelayed({
            scrollView.smoothScrollTo(0, scrollView.bottom)
        }, 1000)
    }
}
