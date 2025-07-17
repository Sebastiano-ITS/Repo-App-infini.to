package com.example.infinito.data.model.ticket

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object TicketUtils {

    private const val PREFS_NAME = "TicketPrefs"
    private const val KEY_TICKETS = "TicketList"

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun saveTickets(context: Context, tickets: List<TicketModel>) {
        val gson = Gson()
        val json = gson.toJson(tickets)
        getSharedPreferences(context).edit { putString(KEY_TICKETS, json) }
    }

    fun getTickets(context: Context): List<TicketModel> {
        val gson = Gson()
        val json = getSharedPreferences(context).getString(KEY_TICKETS, null)
        return if (json != null) {
            val type = object : TypeToken<List<TicketModel>>() {}.type
            gson.fromJson(json, type)
        } else {
            emptyList()
        }
    }
}