package com.example.infinito.utils.language

import android.content.Context
import java.util.Locale
import androidx.core.content.edit
import com.example.infinito.R

fun setLocale(context: Context, languageCode: String): Context {
    val locale = Locale(languageCode)
    Locale.setDefault(locale)
    val config = context.resources.configuration
    config.setLocale(locale)
    config.setLayoutDirection(locale)
    return context.createConfigurationContext(config)
}

fun saveLanguage(context: Context, languageCode: String) {
    val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    prefs.edit {
        putString("language", languageCode)
        apply()
    }
}

fun saveFlag(context: Context, flagCode: Int) {
    val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    prefs.edit {
        putInt("flag", flagCode)
        apply()
    }
}

fun getSavedLanguage(context: Context): String {
    val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    return prefs.getString("language", "it") ?: "it"
}

fun getSavedFlag(context: Context): Int {
    val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    return prefs.getInt("flag", R.drawable.ic_flag_it)
}