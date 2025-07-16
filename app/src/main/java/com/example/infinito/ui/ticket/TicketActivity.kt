package com.example.infinito.ui.ticket

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.infinito.R
import com.example.infinito.data.model.ActivityNames
import com.example.infinito.ui.fragment.BottomBarFragment
import com.example.infinito.ui.fragment.ContactFragment
import com.example.infinito.ui.fragment.HeaderFragment
import com.example.infinito.utils.theme.setFixedTheme

class TicketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ticket)
        setFixedTheme(this, window)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.header, HeaderFragment())
            .add(R.id.bottomBar, BottomBarFragment.newInstance(ActivityNames.TICKET))
            .add(R.id.contactMenu, ContactFragment())
            .commit()

        supportFragmentManager.executePendingTransactions()

        supportFragmentManager
            .beginTransaction()
            .hide(supportFragmentManager.findFragmentById(R.id.contactMenu)!!)
            .commit()
    }
}