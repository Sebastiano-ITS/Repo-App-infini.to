package com.example.infinito.ui.event

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.infinito.R
import com.example.infinito.ui.fragment.BottomBarFragment
import com.example.infinito.ui.fragment.HeaderFragment
import com.example.infinito.utils.theme.setFixedTheme
import com.google.android.material.card.MaterialCardView
import androidx.fragment.app.Fragment
import com.example.infinito.ui.fragment.GeneralFragment
import com.example.infinito.ui.fragment.NewsFragment
import androidx.core.content.ContextCompat
import android.widget.ImageView
import android.widget.TextView


class EventActivity : AppCompatActivity() {

    private lateinit var cardGenerale: MaterialCardView
    private lateinit var iconGenerale: ImageView
    private lateinit var textGenerale: TextView

    private lateinit var cardNews: MaterialCardView
    private lateinit var iconNews: ImageView
    private lateinit var textNews: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)
        setFixedTheme(this, window)

        val activeTab = intent.getStringExtra("active_tab") ?: "home"

        cardGenerale = findViewById(R.id.cardGenerale)
        iconGenerale = findViewById(R.id.iconGenerale)
        textGenerale = findViewById(R.id.textGenerale)

        cardNews = findViewById(R.id.cardNews)
        iconNews = findViewById(R.id.iconNews)
        textNews = findViewById(R.id.textNews)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.header, HeaderFragment())
            .add(R.id.bottomBar, BottomBarFragment.newInstance(activeTab))
            .commit()


        if (savedInstanceState == null) {
            when (activeTab) {
                "home" -> {
                    replaceFragment(GeneralFragment())
                    updateCardState(
                        cardGenerale,
                        iconGenerale,
                        textGenerale,
                        true
                    )
                    updateCardState(
                        cardNews,
                        iconNews,
                        textNews,
                        false
                    )
                }

                "news" -> {
                    replaceFragment(NewsFragment())
                    updateCardState(
                        cardGenerale,
                        iconGenerale,
                        textGenerale,
                        false
                    )
                    updateCardState(
                        cardNews,
                        iconNews,
                        textNews,
                        true
                    )
                }
                else -> {
                    replaceFragment(GeneralFragment())
                    updateCardState(cardGenerale, iconGenerale, textGenerale, true)
                    updateCardState(cardNews, iconNews, textNews, false)
                }
            }
        }



        val cardGenerale: MaterialCardView = findViewById(R.id.cardGenerale)
        val cardNews: MaterialCardView = findViewById(R.id.cardNews)


        if (savedInstanceState == null) {
            replaceFragment(GeneralFragment())
        }


        cardGenerale.setOnClickListener {
            Toast.makeText(this, "Hai cliccato su Generale", Toast.LENGTH_SHORT).show()
            replaceFragment(GeneralFragment())
            updateCardState(cardGenerale, iconGenerale, textGenerale, true)
            updateCardState(cardNews, iconNews, textNews, false)
        }

        cardNews.setOnClickListener {
            Toast.makeText(this, "Hai cliccato su News", Toast.LENGTH_SHORT).show()
            replaceFragment(NewsFragment())
            updateCardState(cardGenerale, iconGenerale, textGenerale, false)
            updateCardState(cardNews, iconNews, textNews, true)
        }


    }



    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_content_container, fragment)
            .commit()
    }

    private fun generateDummyData(): List<CardItem> {
        return listOf(
            CardItem(
                R.drawable.planetario_telescopio,
                "PLANETARIO + TELESCOPIO\n(nuova serata osservativa)"
            ),
            CardItem(R.drawable.museo_astrotalk, "MUSEO + ASTROTALK"),
            CardItem(
                R.drawable.spazio_bambini,
                "LO SPAZIO AI BAMBINI\n(solo il primo week end del mese)"
            ),
            CardItem(R.drawable.cinema_sotto_stelle, "CINEMA SOTTO LE STELLE\napertura serate")
        )
    }

    private fun updateCardState(
        card: MaterialCardView,
        icon: ImageView,
        text: TextView,
        isSelected: Boolean
    ) {
        val selectedColor = ContextCompat.getColor(this, R.color.yellow)
        val unselectedColor = ContextCompat.getColor(this, R.color.white)

        if (isSelected) {
            icon.setColorFilter(selectedColor)
            //text.setTextColor(selectedColor)
            //card.strokeColor = selectedColor
            card.strokeWidth = 1.dpToPx(this)
        } else {
            icon.setColorFilter(unselectedColor)
            //text.setTextColor(unselectedColor)
            //card.strokeColor = unselectedColor
            card.strokeWidth = 1.dpToPx(this)
        }
    }

    // per convertire dp in px
    internal fun Int.dpToPx(context: Context): Int {
        return (this * context.resources.displayMetrics.density + 0.5f).toInt()
    }
}