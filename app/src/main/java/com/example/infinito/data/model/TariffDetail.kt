package com.example.infinito.data.model

import java.io.Serializable


data class TariffDetail(
    val tipo: String,
    val fascia_eta: String? = null, // Può essere nullo se c'è 'categoria'
    val categoria: String? = null,  // Può essere nullo se c'è 'fascia_eta'
    val prezzo: Double,
    val prevendita: Double,
    val totale: Double // Questo campo è già calcolato nel JSON, lo useremo direttamente
) : Serializable {
    // Proprietà helper per ottenere il nome da visualizzare nel pulsante
    val displayName: String
        get() = if (fascia_eta != null) {
            "${tipo} - ${fascia_eta}"
        } else if (categoria != null) {
            "${tipo} - ${categoria}"
        } else {
            tipo // Fallback se non ci sono né fascia_eta né categoria (es. "Mondo Parchi")
        }
}