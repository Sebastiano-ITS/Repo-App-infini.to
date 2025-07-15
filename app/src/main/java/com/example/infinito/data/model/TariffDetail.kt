package com.example.infinito.data.model

import android.content.Context
import java.io.Serializable


data class TariffDetail(
    val tipo: Int,
    val fascia_eta: Int? = null, // Può essere nullo se c'è 'categoria'
    val categoria: Int? = null,  // Può essere nullo se c'è 'fascia_eta'
    val prezzo: Double,
    val prevendita: Double,
    val totale: Double // Questo campo è già calcolato nel JSON, lo useremo direttamente
) : Serializable {
    // Proprietà helper per ottenere il nome da visualizzare nel pulsante
    fun getDisplayName(context: Context): String {
        val tipoString = context.getString(tipo)
        return if (fascia_eta != null) {
            val fasciaEtaString = context.getString(fascia_eta)
            "$tipoString - $fasciaEtaString"
        } else if (categoria != null) {
            val categoriaString = context.getString(categoria)
            "$tipoString - $categoriaString"
        } else {
            tipoString
        }
    }
}