package nl.entreco.scrumpoker.poker.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.databinding.Bindable
import nl.entreco.scrumpoker.poker.PokerCardView
import nl.entreco.scrumpoker.poker.ui.poker.PokerCardModel

class PokerStackedView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        clipToPadding = false
    }

    @Bindable
    fun setCards(cards: List<PokerCardModel>) {
        cards.forEach { card ->
            addView(PokerCardView(context, card, this))
        }
    }
}