package com.example.infinito.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import com.example.infinito.R
import androidx.core.content.res.ResourcesCompat
import com.example.infinito.data.model.TariffModel

// Interfaccia per comunicare la tariffa selezionata all'Activity chiamante
interface OnTariffSelectedListener {
    fun onTariffSelected(selectedTariff: TariffModel)
}

class TariffBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var tariffButtonsContainer: LinearLayout
    private lateinit var confirmTariffButton: MaterialButton
    private var selectedTariff: TariffModel? = null
    private var selectedTariffTextView: TextView? = null // Per tenere traccia del TextView attualmente selezionato

    var onTariffSelectedListener: OnTariffSelectedListener? = null

    private var availableTariffs: List<TariffModel> = emptyList() // Verrà popolata dagli argomenti

    companion object {
        const val ARG_AVAILABLE_TARIFFS = "available_tariffs"

        // Metodo factory per creare un'istanza del fragment con gli argomenti
        fun newInstance(tariffs: List<TariffModel>): TariffBottomSheetFragment {
            val fragment = TariffBottomSheetFragment()
            val args = Bundle()
            args.putSerializable(ARG_AVAILABLE_TARIFFS, ArrayList(tariffs)) // Passa come ArrayList di Serializable
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Recupera le tariffe dagli argomenti
        @Suppress("UNCHECKED_CAST")
        arguments?.getSerializable(ARG_AVAILABLE_TARIFFS)?.let {
            availableTariffs = it as List<TariffModel>
        }
    }

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme // Riutilizza lo stesso tema

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_tariff_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tariffButtonsContainer = view.findViewById(R.id.tariffButtonsContainer)
        confirmTariffButton = view.findViewById(R.id.confirmTariffButton)

        setupTariffButtons()

        confirmTariffButton.setOnClickListener {
            selectedTariff?.let {
                onTariffSelectedListener?.onTariffSelected(it)
                dismiss() // Chiudi il Bottom Sheet
            } ?: run {
                Toast.makeText(context, "Seleziona una tariffa", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupTariffButtons() {
        tariffButtonsContainer.removeAllViews()

        val marginVertical = resources.getDimensionPixelSize(R.dimen.tariff_button_margin_vertical)
        val marginHorizontal = resources.getDimensionPixelSize(R.dimen.tariff_button_margin_horizontal)
        val padding = resources.getDimensionPixelSize(R.dimen.tariff_button_padding)

        val customFont = ResourcesCompat.getFont(requireContext(), R.font.orbitron)

        for (tariff in availableTariffs) {


            val tariffTextView = TextView(context).apply {
                text = tariff.getDisplayName(requireContext())
                textSize = 16f
                gravity = View.TEXT_ALIGNMENT_GRAVITY
                setTextColor(ContextCompat.getColor(requireContext(), R.color.white)) // Testo bianco di default
                background = ContextCompat.getDrawable(requireContext(), R.drawable.selector_tariff_button)
                isClickable = true
                isFocusable = true
                setPadding(padding, padding, padding, padding)

                customFont?.let {
                    typeface = it
                }
            }

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(marginHorizontal, marginVertical, marginHorizontal, marginVertical)
            }
            tariffTextView.layoutParams = params

            tariffTextView.setOnClickListener {
                // Deseleziona il precedente:
                // Imposta il suo background allo stato normale e il testo a bianco
                selectedTariffTextView?.apply {
                    isSelected = false
                    setTextColor(ContextCompat.getColor(context, R.color.white))
                }

                // Seleziona il nuovo:
                // Imposta il suo background allo stato selezionato e il testo a nero
                it.isSelected = true
                (it as TextView).setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                selectedTariffTextView = it
                selectedTariff = tariff
            }
            tariffButtonsContainer.addView(tariffTextView)
        }
    }
}