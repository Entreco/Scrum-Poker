package nl.entreco.scrumpoker.poker.ui.poker

import android.util.Log
import nl.entreco.scrumpoker.poker.ui.poker.stack.StackAnimator
import nl.entreco.scrumpoker.poker.ui.poker.stack.StackClicker

class StackLayoutManager : CardLayoutManager, StackClicker.OnSkipListener, StackClicker.OnClickListener {
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

    override fun onThrownAway(view: PokerCardView) {
        // Swap
        pokerCards.remove(view)
        pokerCards.add(0, view)
        pokerCards.forEachIndexed { index, card ->
            setState(card, CardState.Stacking(index, pokerCards.size - 1))
        }
    }

    override fun onClickedWhileSelecting(view: PokerCardView) {
        setState(view, CardState.Hiding(pokerCards.size - 1, pokerCards.size - 1))
    }

    override fun onClickedWhileHiding(view: PokerCardView) {
        setState(view, CardState.Showing(pokerCards.size - 1, pokerCards.size - 1))
    }

    override fun onClickedWhileShowing(view: PokerCardView) {
        animator.render(view, CardState.Flinging())
        onThrownAway(view)
    }
}
