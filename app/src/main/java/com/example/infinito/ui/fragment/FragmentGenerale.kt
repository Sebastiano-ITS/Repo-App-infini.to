package com.example.infinito.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.infinito.R
import com.example.infinito.ui.event.CardAdapter
import com.example.infinito.ui.event.CardItem

class GeneralFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_general, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val cardItems = generateDummyData()
        val adapter = CardAdapter(cardItems)
        recyclerView.adapter = adapter

        return view
    }

    private fun generateDummyData(): List<CardItem> {
        return listOf(
            CardItem(R.drawable.planetario_telescopio, "PLANETARIO + TELESCOPIO\n(nuova serata osservativa)"),
            CardItem(R.drawable.museo_astrotalk, "MUSEO + ASTROTALK"),
            CardItem(R.drawable.spazio_bambini, "LO SPAZIO AI BAMBINI\n(solo il primo week end del mese)"),
            CardItem(R.drawable.cinema_sotto_stelle, "CINEMA SOTTO LE STELLE\napertura serate")
        )
    }
}