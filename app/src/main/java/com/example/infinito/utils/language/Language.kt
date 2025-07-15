package com.example.infinito.utils.language

import android.content.Context
import java.util.Locale
import androidx.core.content.edit

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

fun getSavedLanguage(context: Context): String {
    val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    return prefs.getString("language", "it") ?: "it"
}