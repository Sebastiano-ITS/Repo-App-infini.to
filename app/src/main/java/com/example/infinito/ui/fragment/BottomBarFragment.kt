package com.example.infinito.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.infinito.R
import com.example.infinito.ui.event.EventActivity
import com.example.infinito.ui.home.HomeActivity
import com.example.infinito.ui.ticket.TicketActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BottomBarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BottomBarFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    //private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
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

        val activeTab = param1

        val yellow = ContextCompat.getColor(view.context, R.color.yellow)

        when (activeTab) {
            "home" -> {
                view.findViewById<ImageView>(R.id.homeImage).setColorFilter(yellow)
                view.findViewById<TextView>(R.id.homeText).setTextColor(yellow)
            }
            "event" -> {
                view.findViewById<ImageView>(R.id.eventImage).setColorFilter(yellow)
                view.findViewById<TextView>(R.id.eventText).setTextColor(yellow)
            }
            "ticket" -> {
                view.findViewById<ImageView>(R.id.ticketImage).setColorFilter(yellow)
                view.findViewById<TextView>(R.id.ticketText).setTextColor(yellow)
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BottomBarFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
            BottomBarFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    //putString(ARG_PARAM2, param2)
                }
            }
    }
}