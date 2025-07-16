package com.example.infinito.utils.theme

import android.content.Context
import android.view.Window
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.infinito.R

fun setFixedTheme(context: Context, window: Window) {

    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

    // Status bar: nera con icone chiare
    window.statusBarColor = ContextCompat.getColor(context, R.color.black)

    // Navigation bar: nera con icone chiare
    window.navigationBarColor = ContextCompat.getColor(context, R.color.black)

    val controller = WindowInsetsControllerCompat(window, window.decorView)
    controller.isAppearanceLightStatusBars = false   // icone chiare su status bar scura
    controller.isAppearanceLightNavigationBars = false // icone chiare su nav bar scura
}
