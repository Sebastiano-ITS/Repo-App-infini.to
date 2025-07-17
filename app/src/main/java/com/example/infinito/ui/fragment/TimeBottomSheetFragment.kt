package com.example.infinito.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import com.example.infinito.R


interface OnTimeSelectedListener {
    fun onTimeSelected(selectedTime: String)
}

class TimeBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var timeGrid: GridLayout
    private lateinit var confirmTimeButton: MaterialButton
    private var selectedTime: String? = null
    private var selectedTimeTextView: TextView? = null

    var onTimeSelectedListener: OnTimeSelectedListener? = null


    private var availableTimes: List<String> = emptyList()

    companion object {
        const val ARG_AVAILABLE_TIMES = "available_times"

        // Metodo factory per creare un'istanza del fragment con gli argomenti
        fun newInstance(times: List<String>): TimeBottomSheetFragment {
            val fragment = TimeBottomSheetFragment()
            val args = Bundle()
            args.putStringArrayList(ARG_AVAILABLE_TIMES, ArrayList(times)) // Passa come ArrayList
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Recupera gli orari dagli argomenti
        arguments?.getStringArrayList(ARG_AVAILABLE_TIMES)?.let {
            availableTimes = it
        }
    }


    override fun getTheme(): Int = R.style.BottomSheetDialogTheme


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_time_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        timeGrid = view.findViewById(R.id.timeGrid)
        confirmTimeButton = view.findViewById(R.id.confirmTimeButton)

        setupTimeButtons()

        confirmTimeButton.setOnClickListener {
            selectedTime?.let {
                onTimeSelectedListener?.onTimeSelected(it)
                dismiss() // Chiude il Bottom Sheet
            } ?: run {
                Toast.makeText(context, "Seleziona un orario", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupTimeButtons() {

        timeGrid.removeAllViews()

        val padding = resources.getDimensionPixelSize(R.dimen.time_button_padding)
        val margin = resources.getDimensionPixelSize(R.dimen.time_button_margin)

        val customFont = ResourcesCompat.getFont(requireContext(), R.font.orbitron)


        for (time in availableTimes) {
            val timeTextView = TextView(context).apply {
                text = time
                textSize = 24f
                gravity = View.TEXT_ALIGNMENT_GRAVITY
                setTextColor(ContextCompat.getColor(context, R.color.black))
                background = ContextCompat.getDrawable(context, R.drawable.selector_time_button)
                isClickable = true
                isFocusable = true
                setPadding(padding, padding, padding, padding)

                customFont?.let {
                    typeface = it
                }
            }

            val params = GridLayout.LayoutParams().apply {
                width = GridLayout.LayoutParams.WRAP_CONTENT
                height = GridLayout.LayoutParams.WRAP_CONTENT
                columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                setMargins(margin, margin, margin, margin)
            }
            timeTextView.layoutParams = params

            timeTextView.setOnClickListener {
                selectedTimeTextView?.isSelected = false
                it.isSelected = true
                selectedTimeTextView = it as TextView
                selectedTime = timeTextView.text.toString()
            }
            timeGrid.addView(timeTextView)
        }
    }
}