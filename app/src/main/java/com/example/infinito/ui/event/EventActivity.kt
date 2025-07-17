package com.example.infinito.ui.event

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.infinito.R
import com.example.infinito.data.model.ActivityNames
import com.example.infinito.ui.fragment.BottomBarFragment
import com.example.infinito.ui.fragment.ContactFragment
import com.example.infinito.ui.fragment.GeneralFragment
import com.example.infinito.ui.fragment.HeaderFragment
import com.example.infinito.ui.fragment.NewsFragment
import com.example.infinito.utils.theme.setFixedTheme
import com.google.android.material.card.MaterialCardView


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

        cardGenerale = findViewById(R.id.cardGenerale)
        iconGenerale = findViewById(R.id.iconGenerale)
        textGenerale = findViewById(R.id.textGenerale)

        cardNews = findViewById(R.id.cardNews)
        iconNews = findViewById(R.id.iconNews)
        textNews = findViewById(R.id.textNews)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.header, HeaderFragment())
            .add(R.id.bottomBar, BottomBarFragment.newInstance(ActivityNames.EVENT))
            .add(R.id.contactMenu, ContactFragment())
            .commit()

        supportFragmentManager.executePendingTransactions()

        supportFragmentManager
            .beginTransaction()
            .hide(supportFragmentManager.findFragmentById(R.id.contactMenu)!!)
            .commit()

        val cardGenerale: MaterialCardView = findViewById(R.id.cardGenerale)
        val cardNews: MaterialCardView = findViewById(R.id.cardNews)

        if (savedInstanceState == null) {
            replaceFragment(GeneralFragment())
        }

        cardGenerale.setOnClickListener {
            replaceFragment(GeneralFragment())
            updateCardState(cardGenerale, iconGenerale, true)
            updateCardState(cardNews, iconNews, false)
        }

        cardNews.setOnClickListener {
            replaceFragment(NewsFragment())
            updateCardState(cardGenerale, iconGenerale, false)
            updateCardState(cardNews, iconNews, true)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_content_container, fragment)
            .commit()
    }

    private fun updateCardState(
        card: MaterialCardView,
        icon: ImageView,
        isSelected: Boolean
    ) {
        val selectedColor = ContextCompat.getColor(this, R.color.yellow)
        val unselectedColor = ContextCompat.getColor(this, R.color.white)

        if (isSelected) {
            icon.setColorFilter(selectedColor)
            card.strokeWidth = 1.dpToPx(this)
        } else {
            icon.setColorFilter(unselectedColor)
            card.strokeWidth = 1.dpToPx(this)
        }
    }

    // per convertire dp in px
    private fun Int.dpToPx(context: Context): Int {
        return (this * context.resources.displayMetrics.density + 0.5f).toInt()
    }
}