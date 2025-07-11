package com.example.infinito.ui.event



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import com.example.infinito.R // Assicurati di importare la tua risorsa R

interface OnDateSelectedListener {
    fun onDateSelected(selectedDate: LocalDate)
}

class CalendarBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var calendarView: CalendarView
    private lateinit var confirmButton: MaterialButton
    private var selectedDate: LocalDate? = null

    var onDateSelectedListener: OnDateSelectedListener? = null

    // Sovrascrivi getTheme per applicare il tema personalizzato
    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_calendar_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Questo è il layout radice del contenuto del Bottom Sheet.
        // Se vuoi assicurarti che anche il "dialog" stesso sia nero,
        // potresti voler modificare il colore di sfondo del dialog.
        // Però, l'approccio con il tema è solitamente migliore.
        // Per fare un test rapido del colore del background del dialog:
        // (Da usare con cautela, il tema è preferibile)
        // (dialog as? BottomSheetDialog)?.behavior?.peekHeight = resources.displayMetrics.heightPixels // Rende il bottom sheet alto quanto lo schermo
        // (view.parent as? View)?.setBackgroundColor(resources.getColor(R.color.black, null)) // Questo potrebbe funzionare per colorare il contenitore del dialog

        calendarView = view.findViewById(R.id.calendarView)
        confirmButton = view.findViewById(R.id.confirmDateButton)

        val todayCalendar = Calendar.getInstance()
        calendarView.date = todayCalendar.timeInMillis
        selectedDate = LocalDate.of(
            todayCalendar.get(Calendar.YEAR),
            todayCalendar.get(Calendar.MONTH) + 1,
            todayCalendar.get(Calendar.DAY_OF_MONTH)
        )

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
            Toast.makeText(context, "Data selezionata: ${selectedDate?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}", Toast.LENGTH_SHORT).show()
        }

        confirmButton.setOnClickListener {
            selectedDate?.let {
                onDateSelectedListener?.onDateSelected(it)
                dismiss()
            } ?: run {
                Toast.makeText(context, "Seleziona una data", Toast.LENGTH_SHORT).show()
            }
        }
    }
}