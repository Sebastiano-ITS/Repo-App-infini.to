package com.example.infinito.ui.start

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.View
import com.example.infinito.ui.home.HomeActivity
import com.example.infinito.R

class FullscreenVideoView(context: Context, attrs: AttributeSet?) : VideoView(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        setMeasuredDimension(width, height)
    }
}

class StartActivity : AppCompatActivity() {

    private lateinit var videoBackground: VideoView
    private lateinit var startButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Rendi fullscreen
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                )
        actionBar?.hide() // oppure supportActionBar?.hide() se usi AppCompatActivity

        setContentView(R.layout.activity_start)

        videoBackground = findViewById(R.id.videoBackground)
        startButton = findViewById(R.id.startButton)

        // Percorso video in res/raw (metti video_background.mp4 dentro res/raw)
        val videoUri = Uri.parse("android.resource://${packageName}/${R.raw.video_background}")
        videoBackground.setVideoURI(videoUri)
        videoBackground.setOnPreparedListener { mediaPlayer ->
            mediaPlayer.isLooping = true
            mediaPlayer.setVolume(0f, 0f) // silenzia audio
            videoBackground.start()
        }

        val startButton = findViewById<Button>(R.id.startButton)
        startButton.setOnClickListener {
            val homeIntent = Intent(this, HomeActivity::class.java)
            startActivity(homeIntent)
            finish()
        }


    }

    override fun onResume() {
        super.onResume()
        videoBackground.start()
    }

    override fun onPause() {
        super.onPause()
        videoBackground.pause()
    }
}