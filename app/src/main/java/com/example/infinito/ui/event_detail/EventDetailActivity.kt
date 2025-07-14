package com.example.infinito.ui.event_detail

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.infinito.R
import com.example.infinito.ui.fragment.HeaderFragment
import com.example.infinito.utils.theme.setFixedTheme
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.Calendar

class EventDetailActivity : AppCompatActivity() {

    private lateinit var calendarText: TextView
    private lateinit var clockText: TextView
    private lateinit var btnShop: Button

    private var selectedDate: String = ""
    private var selectedTime: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_event_detail)
        setFixedTheme(this, window)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.header, HeaderFragment())
            .commit()

        calendarText = findViewById(R.id.tv_calendar)
        clockText = findViewById(R.id.tv_clock)

        calendarText.setOnClickListener {
            showCalendarDialog()
        }

        clockText.setOnClickListener {
            showTimeDialog()
        }

        btnShop = findViewById(R.id.btn_acquista)

        btnShop.setOnClickListener {
            Log.d("datalosa", selectedDate)
        }
    }

    private fun showCalendarDialog() {
        val dialog = BottomSheetDialog(this)
        val dialogView = layoutInflater.inflate(R.layout.calendar_dialog,null)

        val datePicker = dialogView.findViewById<DatePicker>(R.id.calendar_view)
        val btnConfirm = dialogView.findViewById<Button>(R.id.btn_confirm)

        var tempDate = ""

        if (selectedDate.isNotEmpty()) {
            val splitDate = selectedDate.split("-")
            val calendar = Calendar.getInstance()
            calendar.set(splitDate[0].toInt(), splitDate[1].toInt(), splitDate[2].toInt())
            datePicker.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
        }

        datePicker.setOnDateChangedListener { _, year, month, day ->
            tempDate = "$year-$month-$day"
        }

        btnConfirm.setOnClickListener {
            selectedDate = tempDate
            dialog.dismiss()
        }

        dialog.setContentView(dialogView)
        dialog.show()
    }

    private fun showTimeDialog() {
        val dialog = BottomSheetDialog(this)
        val dialogView = layoutInflater.inflate(R.layout.time_dialog,null)

        val fourteenBtn = dialogView.findViewById<Button>(R.id.fourteenBtn)
        val sixteenBtn = dialogView.findViewById<Button>(R.id.sixteenBtn)
        val seventeenBtn = dialogView.findViewById<Button>(R.id.seventeenBtn)
        val eighteenBtn = dialogView.findViewById<Button>(R.id.eighteenBtn)
        val btnConfirm = dialogView.findViewById<Button>(R.id.btn_confirm)

        var tempTime = ""

        if (selectedTime.isNotEmpty()) {
            when (selectedTime) {
                "14:30" -> fourteenBtn.setBackgroundResource(R.drawable.selected_time)
                "16:00" -> sixteenBtn.setBackgroundResource(R.drawable.selected_time)
                "17:30" -> seventeenBtn.setBackgroundResource(R.drawable.selected_time)
                "18:45" -> eighteenBtn.setBackgroundResource(R.drawable.selected_time)
            }
        }

        fourteenBtn.setOnClickListener {
            eighteenBtn.setBackgroundResource(R.drawable.not_selected_time)
            seventeenBtn.setBackgroundResource(R.drawable.not_selected_time)
            sixteenBtn.setBackgroundResource(R.drawable.not_selected_time)
            fourteenBtn.setBackgroundResource(R.drawable.selected_time)
            tempTime = "14:30"
        }

        sixteenBtn.setOnClickListener {
            eighteenBtn.setBackgroundResource(R.drawable.not_selected_time)
            seventeenBtn.setBackgroundResource(R.drawable.not_selected_time)
            sixteenBtn.setBackgroundResource(R.drawable.selected_time)
            fourteenBtn.setBackgroundResource(R.drawable.not_selected_time)
            tempTime = "16:00"
        }

        seventeenBtn.setOnClickListener {
            eighteenBtn.setBackgroundResource(R.drawable.not_selected_time)
            seventeenBtn.setBackgroundResource(R.drawable.selected_time)
            sixteenBtn.setBackgroundResource(R.drawable.not_selected_time)
            fourteenBtn.setBackgroundResource(R.drawable.not_selected_time)
            tempTime = "17:30"
        }

        eighteenBtn.setOnClickListener {
            eighteenBtn.setBackgroundResource(R.drawable.selected_time)
            seventeenBtn.setBackgroundResource(R.drawable.not_selected_time)
            sixteenBtn.setBackgroundResource(R.drawable.not_selected_time)
            fourteenBtn.setBackgroundResource(R.drawable.not_selected_time)
            tempTime = "18:45"
        }

        btnConfirm.setOnClickListener {
            selectedTime = tempTime
            dialog.dismiss()
        }

        dialog.setContentView(dialogView)
        dialog.show()
    }
}