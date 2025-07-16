package com.example.infinito.ui.profile

import android.app.TaskStackBuilder
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.infinito.MainActivity
import com.example.infinito.R
import com.example.infinito.ui.fragment.ConfirmDialogFragment
import com.example.infinito.ui.home.HomeActivity
import com.example.infinito.ui.login.LoginActivity
import com.example.infinito.utils.UserUtils
import com.example.infinito.utils.language.getSavedFlag
import com.example.infinito.utils.language.getSavedLanguage
import com.example.infinito.utils.language.saveFlag
import com.example.infinito.utils.language.saveLanguage
import com.example.infinito.utils.theme.setFixedTheme

class ProfileActivity : AppCompatActivity() {
    
    private lateinit var fullNameText: TextView
    private lateinit var emailText: TextView
    private lateinit var changeLanguageBtn: LinearLayout
    private lateinit var flagIcon: ImageView
    private lateinit var exitAccountBtn: Button
    private lateinit var deleteAccountBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        setFixedTheme(this, window)

        val user = UserUtils.getUsers(this).filter { e -> e.isLoggedIn }[0]

        val fullName = "${user.name} ${user.surname}"

        fullNameText = findViewById(R.id.fullNameText)
        emailText = findViewById(R.id.emailText)
        changeLanguageBtn = findViewById(R.id.changeLanguageBtn)
        flagIcon = findViewById(R.id.flagIcon)
        exitAccountBtn = findViewById(R.id.exitAccountBtn)
        deleteAccountBtn = findViewById(R.id.deleteAccountBtn)

        fullNameText.text = fullName
        emailText.text = user.email
        flagIcon.setBackgroundResource(getSavedFlag(this))

        changeLanguageBtn.setOnClickListener {
            if (getSavedLanguage(this) == "it" && getSavedFlag(this) == R.drawable.ic_flag_it) {
                saveLanguage(this, "en")
                saveFlag(this, R.drawable.ic_flag_uk)
            } else {
                saveLanguage(this, "it")
                saveFlag(this, R.drawable.ic_flag_it)
            }

            val mainIntent = Intent(this, MainActivity::class.java)
            mainIntent.putExtra("languageChanged", true)
            startActivity(mainIntent)
            overridePendingTransition(0, 0)
        }

        exitAccountBtn.setOnClickListener {
            val indexToReplace = UserUtils.getUsers(this).indexOfFirst { it == user }
            val users = UserUtils.getUsers(this).toMutableList()
            user.isLoggedIn = false
            users[indexToReplace] = user
            UserUtils.saveUsers(this, users)
            val loginIntent = Intent(this, LoginActivity::class.java)
            loginIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(loginIntent)
            finish()
        }

        deleteAccountBtn.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.confirmDialog, ConfirmDialogFragment.newInstance(user))
                .addToBackStack(null)
                .commit()
        }
    }
}