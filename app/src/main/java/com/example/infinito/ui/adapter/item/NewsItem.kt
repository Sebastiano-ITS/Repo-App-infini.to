package com.example.infinito.ui.adapter.item

data class NewsItem(
    val imageResId: Int,    // ID risorsa immagine (es. R.drawable.italia_spazio)
    val title: Int,      // Titolo dell'articolo
    val url: Int         // URL della pagina web da aprire
)