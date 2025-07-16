package com.example.infinito

import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.infinito.ui.home.HomeActivity
import com.example.infinito.ui.profile.ProfileActivity
import com.example.infinito.ui.start.StartActivity
import com.example.infinito.utils.UserUtils
import com.example.infinito.utils.language.getSavedLanguage
import com.example.infinito.utils.language.setLocale

class MainActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context) {
        val lang = getSavedLanguage(newBase)
        val context = setLocale(newBase, lang)
        super.attachBaseContext(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val users = UserUtils.getUsers(this)

        var isLoggedIn = false

        val fromProfile = intent.getBooleanExtra("languageChanged", false)

        if (fromProfile) {
            isLoggedIn = true
            val stackBuilder = TaskStackBuilder.create(this)
            stackBuilder.addNextIntent(Intent(this, HomeActivity::class.java))
            stackBuilder.addNextIntent(Intent(this, ProfileActivity::class.java))
            stackBuilder.startActivities()
            finish()
        } else if (users.isNotEmpty()) {
            users.forEach { e ->
                if (e.isLoggedIn) {
                    isLoggedIn = true
                    val homeIntent = Intent(this, HomeActivity::class.java)
                    startActivity(homeIntent)
                    finish()
                }
            }
        }

        if (!isLoggedIn) {
            val startIntent = Intent(this, StartActivity::class.java)
            startActivity(startIntent)
            finish()
        }
    }
}