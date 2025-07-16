package com.example.infinito.ui.home

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.infinito.R
import com.example.infinito.data.model.ActivityNames
import com.example.infinito.ui.fragment.BottomBarFragment
import com.example.infinito.ui.fragment.ContactFragment
import com.example.infinito.ui.fragment.HeaderFragment
import com.example.infinito.utils.theme.setFixedTheme
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class HomeActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        setFixedTheme(this, window)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.header, HeaderFragment())
            .add(R.id.bottomBar, BottomBarFragment.newInstance(ActivityNames.HOME))
            .add(R.id.contactMenu, ContactFragment())
            .commit()

        supportFragmentManager.executePendingTransactions()

        supportFragmentManager
            .beginTransaction()
            .hide(supportFragmentManager.findFragmentById(R.id.contactMenu)!!)
            .commit()

        viewPagerAdapter = ViewPagerAdapter(this)
        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = viewPagerAdapter

        val dotsIndicator = findViewById<DotsIndicator>(R.id.dotsIndicator)
        dotsIndicator.attachTo(viewPager)

        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })
    }
}