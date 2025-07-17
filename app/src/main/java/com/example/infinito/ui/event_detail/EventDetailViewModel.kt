package com.example.infinito.ui.event_detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.infinito.data.model.InfinitoApiResponse
import com.example.infinito.data.model.InfinitoItem
import com.example.infinito.data.network.RetrofitClient
import kotlinx.coroutines.launch

class EventDetailViewModel : ViewModel() {

    var dayList = MutableLiveData<List<InfinitoItem>>();

    fun fetchAvailableDaysData() {
        viewModelScope.launch {
            try {
                val response: InfinitoApiResponse = RetrofitClient.infinitoApiService.getInfinitoData()

                val items: List<InfinitoItem> = response.dayList

                if (items.isNotEmpty()) {
                    dayList.value = response.dayList
                    println("Primo elemento nella lista: ${items.firstOrNull()}")
                } else {
                    dayList.value = listOf()
                    Log.d("Empty Response", "La lista 'data' è vuota o era null dall'API.")
                }

            } catch (e: Exception) {
                dayList.value = listOf()
                Log.d("Errore fetch Api", "Errore durante il recupero dei dati: ${e.message}")
                e.printStackTrace()
            }
        }
    }
}