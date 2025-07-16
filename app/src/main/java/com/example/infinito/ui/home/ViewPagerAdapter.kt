package com.example.infinito.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.infinito.ui.fragment.ViewPagerCardFragment

class ViewPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {

        // Return a NEW fragment instance in createFragment(int).
        val fragment: Fragment = when (position) {
            0 -> ViewPagerCardFragment.newInstance(0)
            1 -> ViewPagerCardFragment.newInstance(1)
            else -> ViewPagerCardFragment.newInstance(2)
        }

        return fragment
    }
}