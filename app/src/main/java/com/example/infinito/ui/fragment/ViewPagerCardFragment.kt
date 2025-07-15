package com.example.infinito.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.infinito.R

class ViewPagerCardFragment : Fragment() {

    private var pageNumber: Int = 0

    companion object {
        private const val ARG_PAGE_NUMBER = "page_number"

        fun newInstance(pageNumber: Int): ViewPagerCardFragment {
            val fragment = ViewPagerCardFragment()
            val args = Bundle()
            args.putInt(ARG_PAGE_NUMBER, pageNumber)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pageNumber = it.getInt(ARG_PAGE_NUMBER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_view_pager_card, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cardImage = view.findViewById<CardView>(R.id.card_image)

        when (pageNumber) {
            0 -> cardImage.setBackgroundResource(R.drawable.moon_week)
            1 -> cardImage.setBackgroundResource(R.drawable.moon_live)
            2 -> cardImage.setBackgroundResource(R.drawable.space_festival)
        }
    }
}