package com.example.infinito.ui.home

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.infinito.R
import com.example.infinito.data.model.ActivityNames
import com.example.infinito.ui.fragment.BottomBarFragment
import com.example.infinito.ui.fragment.HeaderFragment
import com.example.infinito.utils.theme.setFixedTheme

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        setFixedTheme(this, window)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.header, HeaderFragment())
            .add(R.id.bottomBar, BottomBarFragment.newInstance(ActivityNames.HOME))
            .commit()
    }
}