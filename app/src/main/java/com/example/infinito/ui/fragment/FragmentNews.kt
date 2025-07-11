package com.example.infinito.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.infinito.ui.event.NewsItem // Importa la data class NewsItem
import com.example.infinito.ui.event.NewsAdapter // Importa l'adapter NewsAdapter
import androidx.recyclerview.widget.GridLayoutManager // Importa GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.infinito.R
import com.google.android.material.button.MaterialButton

class NewsFragment : Fragment() {

    private lateinit var newsRecyclerView: RecyclerView
    private lateinit var loadMoreButton: MaterialButton
    private lateinit var newsAdapter: NewsAdapter

    private var allNewsItems: List<NewsItem> = emptyList()
    private val loadedNewsItems: MutableList<NewsItem> = mutableListOf()
    private val ITEMS_PER_LOAD = 4


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)

        newsRecyclerView = view.findViewById(R.id.newsRecyclerView)
        loadMoreButton = view.findViewById(R.id.loadMoreButton)

        newsRecyclerView.layoutManager = GridLayoutManager(context, 2)
        allNewsItems = generateDummyNewsData()
        newsAdapter = NewsAdapter(loadedNewsItems)
        newsRecyclerView.adapter = newsAdapter
        loadMoreArticles()
        loadMoreButton.setOnClickListener {
            loadMoreArticles()
        }

        return view
    }

    private fun loadMoreArticles() {
        val currentSize = loadedNewsItems.size
        val itemsToAdd = allNewsItems.drop(currentSize).take(ITEMS_PER_LOAD)

        if (itemsToAdd.isNotEmpty()) {
            loadedNewsItems.addAll(itemsToAdd)
            newsAdapter.notifyItemRangeInserted(currentSize, itemsToAdd.size)
        }


        if (loadedNewsItems.size >= allNewsItems.size) {
            loadMoreButton.visibility = View.GONE
        } else {
            loadMoreButton.visibility = View.VISIBLE
        }
    }

    private fun generateDummyNewsData(): List<NewsItem> {
        return listOf(
            NewsItem(R.drawable.italia_spazio, "Italia nello spazio", "https://www.planetarioditorino.it/italia-nello-spazio"),
            NewsItem(R.drawable.bentornato_autunno, "Bentornato Autunno", "https://www.planetarioditorino.it/bentornato-autunno"),
            NewsItem(R.drawable.benvenuta_estate, "Benvenuta Estate!", ""),
            NewsItem(R.drawable.primavera, "Quando inizia La Primavera", ""),
            NewsItem(R.drawable.solstizio_inverno, "Solstizio D'Inverno", ""),
            NewsItem(R.drawable.iss_compleanno, "Buon Compleanno, ISS!", ""),
            NewsItem(R.drawable.missione_galileo, "La Missione Galileo", ""),
            NewsItem(R.drawable.cassini_huygens, "La Missione Cassini-Huygens", ""),
            NewsItem(R.drawable.sonde_voyager, "Le Sonde Voyager", ""),
            NewsItem(R.drawable.rover_curiosity, "Il Rover Curiosity", "")
        )
    }
}