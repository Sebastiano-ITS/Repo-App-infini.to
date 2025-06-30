package com.example.infinito.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.infinito.R
import com.example.infinito.data.model.ActivityNames
import com.example.infinito.ui.event.EventActivity
import com.example.infinito.ui.home.HomeActivity
import com.example.infinito.ui.ticket.TicketActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_ACTIVITY_NAME = "activityName"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BottomBarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BottomBarFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var activityName: ActivityNames? = null
    //private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            activityName = it.getString(ARG_ACTIVITY_NAME)
                ?.let { it1 -> ActivityNames.valueOf(it1) }
            //param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom_bar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeButton = view.findViewById<LinearLayout>(R.id.homeButton)
        homeButton.setOnClickListener {
            val homeIntent = Intent(view.context, HomeActivity::class.java)
            homeIntent.putExtra("active_tab", "home")
            startActivity(homeIntent)
            requireActivity().finish()
            requireActivity().overridePendingTransition(0, 0)
        }

        val eventButton = view.findViewById<LinearLayout>(R.id.eventButton)
        eventButton.setOnClickListener {
            val eventIntent = Intent(view.context, EventActivity::class.java)
            eventIntent.putExtra("active_tab", "event")
            startActivity(eventIntent)
            requireActivity().finish()
            requireActivity().overridePendingTransition(0, 0)
        }

        val ticketButton = view.findViewById<LinearLayout>(R.id.ticketButton)
        ticketButton.setOnClickListener {
            val ticketIntent = Intent(view.context, TicketActivity::class.java)
            ticketIntent.putExtra("active_tab", "ticket")
            startActivity(ticketIntent)
            requireActivity().finish()
            requireActivity().overridePendingTransition(0, 0)
        }

        val activeTab = activityName

        val yellow = ContextCompat.getColor(view.context, R.color.yellow)

        when (activeTab) {
            ActivityNames.HOME -> {
                view.findViewById<ImageView>(R.id.homeImage).setColorFilter(yellow)
                view.findViewById<TextView>(R.id.homeText).setTextColor(yellow)
            }
            ActivityNames.EVENT -> {
                view.findViewById<ImageView>(R.id.eventImage).setColorFilter(yellow)
                view.findViewById<TextView>(R.id.eventText).setTextColor(yellow)
            }
            ActivityNames.TICKET -> {
                view.findViewById<ImageView>(R.id.ticketImage).setColorFilter(yellow)
                view.findViewById<TextView>(R.id.ticketText).setTextColor(yellow)
            }
            null -> Log.d("Errore enum ActivityNames", "Non si sa in che activity sono")
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param activityName Activity Name.
         * @return A new instance of fragment BottomBarFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(activityName: ActivityNames) =
            BottomBarFragment().apply {
                arguments = Bundle().apply {
                    putString("activityName", activityName.name)
                    //putString(ARG_PARAM2, param2)
                }
            }
    }
}