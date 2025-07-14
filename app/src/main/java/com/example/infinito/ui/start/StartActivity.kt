package com.example.infinito.ui.start

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.AttributeSet
import android.widget.Button
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.edit
import androidx.core.net.toUri
import com.example.infinito.R
import com.example.infinito.ui.home.HomeActivity

class FullscreenVideoView(context: Context, attrs: AttributeSet?) : VideoView(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        setMeasuredDimension(width, height)
    }
}

class StartActivity : AppCompatActivity() {

    private val PREFS_NAME = "MyInfinitoPrefs"
    private val KEY_FIRST_RUN = "isFirstAppFirstRun"

    private lateinit var videoBackground: VideoView
    private lateinit var startButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val prefs: SharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        setContentView(R.layout.activity_start)

        window.statusBarColor = ContextCompat.getColor(this, R.color.black)

        videoBackground = findViewById(R.id.videoBackground)
        startButton = findViewById(R.id.startButton)

        val videoUri = "android.resource://${packageName}/${R.raw.video_background}".toUri()
        videoBackground.setVideoURI(videoUri)
        videoBackground.setOnPreparedListener { mediaPlayer ->
            mediaPlayer.isLooping = true
            mediaPlayer.setVolume(0f, 0f)
            videoBackground.start()
        }

        startButton.setOnClickListener {

            prefs.edit {
                putBoolean(KEY_FIRST_RUN, false)
            }

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
