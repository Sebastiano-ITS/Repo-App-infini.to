package com.example.infinito.ui.fragment



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.Toast
import com.example.infinito.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import java.time.LocalDate
import java.util.Calendar

interface OnDateSelectedListener {
    fun onDateSelected(selectedDate: LocalDate)
}

class CalendarBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var calendarView: CalendarView
    private lateinit var confirmButton: MaterialButton
    private var selectedDate: LocalDate = LocalDate.of(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH)

    var availabilityList: BooleanArray? = null
    var onDateSelectedListener: OnDateSelectedListener? = null

    // Sovrascrivi getTheme per applicare il tema personalizzato
    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_calendar_bottom_sheet, container, false)
    }

    // Nel CalendarBottomSheetFragment.kt, aggiungi questa configurazione in onViewCreated:

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calendarView = view.findViewById(R.id.calendarView)
        confirmButton = view.findViewById(R.id.confirmDateButton)

        val todayCalendar = Calendar.getInstance()

        // Ottieni il primo giorno del mese corrente
        val startOfMonth = Calendar.getInstance().apply {
            set(Calendar.DAY_OF_MONTH, 1)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        // Ottieni l'ultimo giorno del mese corrente
        val endOfMonth = Calendar.getInstance().apply {
            set(Calendar.DAY_OF_MONTH, getActualMaximum(Calendar.DAY_OF_MONTH))
            set(Calendar.HOUR_OF_DAY, 23)
            set(Calendar.MINUTE, 59)
            set(Calendar.SECOND, 59)
            set(Calendar.MILLISECOND, 999)
        }

        // Configura il CalendarView per limitarlo al mese corrente
        calendarView.minDate = startOfMonth.timeInMillis
        calendarView.maxDate = endOfMonth.timeInMillis

        // Imposta la data corrente
        calendarView.date = todayCalendar.timeInMillis
        selectedDate = LocalDate.of(
            todayCalendar.get(Calendar.YEAR),
            todayCalendar.get(Calendar.MONTH) + 1,
            todayCalendar.get(Calendar.DAY_OF_MONTH)
        )

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val newDate = LocalDate.of(year, month + 1, dayOfMonth)

            // Controlla se availabilityList è disponibile e ha la dimensione corretta
            availabilityList?.let { availability ->
                // Assumendo che l'array availability corrisponda ai giorni del mese corrente
                if (dayOfMonth <= availability.size && availability[dayOfMonth - 1]) {
                    // Giorno disponibile (dayOfMonth - 1 perché gli array iniziano da 0)
                    selectedDate = newDate
                } else {
                    // Giorno non disponibile - ripristina la data precedente
                    val previousDay = Calendar.getInstance()
                    previousDay[Calendar.YEAR] = selectedDate.year
                    previousDay[Calendar.MONTH] = selectedDate.month.value - 1
                    previousDay[Calendar.DAY_OF_MONTH] = selectedDate.dayOfMonth
                    calendarView.date = previousDay.timeInMillis

                    Toast.makeText(context, "Giorno non disponibile", Toast.LENGTH_SHORT).show()
                }
            } ?: run {
                // Se availabilityList non è disponibile, permetti la selezione
                selectedDate = newDate
            }
        }

        confirmButton.setOnClickListener {
            selectedDate.let {
                onDateSelectedListener?.onDateSelected(it)
                dismiss()
            }
        }
    }
}