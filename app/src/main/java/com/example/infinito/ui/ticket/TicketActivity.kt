package com.example.infinito.ui.ticket

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.infinito.R
import com.example.infinito.ui.fragment.BottomBarFragment
import com.example.infinito.ui.fragment.HeaderFragment
import com.example.infinito.utils.theme.setFixedTheme

class TicketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ticket)
        setFixedTheme(this, window)

        val activeTab = intent.getStringExtra("active_tab") ?: "home"

        supportFragmentManager
            .beginTransaction()
            .add(R.id.header, HeaderFragment())
            .add(R.id.bottomBar, BottomBarFragment.newInstance(activeTab))
            .commit()
    }
}