package nl.entreco.scrumpoker.poker.ui.poker

import android.util.Log
import nl.entreco.scrumpoker.poker.ui.poker.stack.StackAnimator
import nl.entreco.scrumpoker.poker.ui.poker.stack.StackClicker

class StackLayoutManager(private val listener: OnCardClickListener) : CardLayoutManager, StackClicker.OnSkipListener, StackClicker.OnClickListener {

    interface OnCardClickListener {
        fun onClicked(view: PokerCardView)
    }

    private val animator: CardAnimator = StackAnimator()
    private val clicker: CardClicker = StackClicker(this, this)
    private val pokerCards = mutableListOf<PokerCardView>()

    override fun setState(view: PokerCardView, state: CardState) {
        Log.i("YOYOYO", "view: ${view.card} state:$state")
        animator.render(view, state)
        clicker.prepare(view, state)
    }

    override fun add(cardView: PokerCardView, index: Int, total: Int) {
        cardView.setState(CardState.Stacking(index, total))
        pokerCards.add(cardView)
    }

    override fun onNextCard(view: PokerCardView) {
        // Swap
        pokerCards.remove(view)
        pokerCards.add(0, view)
        pokerCards.forEachIndexed { index, card ->
            setState(card, CardState.Stacking(index, pokerCards.size - 1))
        }
    }

    override fun onClickedWhileSelecting(view: PokerCardView) {
        listener.onClicked(view)
    }
}
