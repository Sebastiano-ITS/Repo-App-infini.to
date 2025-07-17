package com.example.infinito.data.model.user

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object UserUtils {

    private const val PREFS_NAME = "UserPrefs"
    private const val KEY_USERS = "UserList"

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun saveUsers(context: Context, users: List<UserModel>) {
        val gson = Gson()
        val json = gson.toJson(users)
        getSharedPreferences(context).edit { putString(KEY_USERS, json) }
    }

    fun getUsers(context: Context): List<UserModel> {
        val gson = Gson()
        val json = getSharedPreferences(context).getString(KEY_USERS, null)
        return if (json != null) {
            val type = object : TypeToken<List<UserModel>>() {}.type
            gson.fromJson(json, type)
        } else {
            emptyList()
        }
    }
}