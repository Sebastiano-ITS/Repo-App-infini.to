package com.example.infinito.ui.event

data class NewsItem(
    val imageResId: Int,    // ID risorsa immagine (es. R.drawable.italia_spazio)
    val title: String,      // Titolo dell'articolo
    val url: String         // URL della pagina web da aprire
)