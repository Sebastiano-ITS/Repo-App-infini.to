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
            NewsItem(R.drawable.italia_spazio, R.string.news_title_italy_space, R.string.news_url_italy_space),
            NewsItem(R.drawable.bentornato_autunno, R.string.news_title_welcome_autumn, R.string.news_url_welcome_autumn),
            NewsItem(R.drawable.benvenuta_estate, R.string.news_title_welcome_summer, R.string.news_url_welcome_summer),
            NewsItem(R.drawable.primavera, R.string.news_title_spring, R.string.news_url_spring),
            NewsItem(R.drawable.solstizio_inverno, R.string.news_title_winter_solstice, R.string.news_url_winter_solstice),
            NewsItem(R.drawable.iss_compleanno, R.string.news_title_iss_birthday, R.string.news_url_iss_birthday),
            NewsItem(R.drawable.missione_galileo, R.string.news_title_mission_galileo, R.string.news_url_mission_galileo),
            NewsItem(R.drawable.cassini_huygens, R.string.news_title_cassini_huygens, R.string.news_url_cassini_huygens),
            NewsItem(R.drawable.sonde_voyager, R.string.news_title_sonde_voyager, R.string.news_url_sonde_voyager),
            NewsItem(R.drawable.rover_curiosity, R.string.news_title_rover_curiosity, R.string.news_url_rover_curiosity)
        )
    }
}