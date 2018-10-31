package nl.entreco.scrumpoker.poker.ui.poker.stack

import android.view.View
import nl.entreco.scrumpoker.poker.R
import nl.entreco.scrumpoker.poker.ui.poker.PokerCardView

class HideClicker(private val view: PokerCardView,
                  private val clicker: StackClicker.OnClickListener) : View.OnClickListener {
    override fun onClick(v: View?) {
        clicker.onClickedWhileHiding(view)
    }
}