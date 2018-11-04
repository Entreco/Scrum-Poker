package nl.entreco.scrumpoker.poker.ui.poker.stack

import nl.entreco.scrumpoker.poker.ui.poker.CardClicker
import nl.entreco.scrumpoker.poker.ui.poker.CardState
import nl.entreco.scrumpoker.poker.ui.poker.PokerCardView

class StackClicker(
    private val clickListener: OnClickListener,
    skipListener: OnSkipListener): CardClicker {

    private val animator = StackAnimator()
    private val dragListener = animator as OnDragListener
    private val skipListeners = listOf(animator as OnSkipListener, skipListener)

    override fun prepare(card: PokerCardView, state: CardState) {
        when(state){
            is CardState.Stacking -> setupStackClicker(card, state.index, state.total)
        }
    }

    private fun setupStackClicker(view: PokerCardView,index: Int, total: Int) {
        view.setOnTouchListener(if (index == total) StackToucher(view, dragListener, clickListener, skipListeners) else null)
    }

    interface OnSkipListener {
        fun onNextCard(view: PokerCardView)
    }

    interface OnDragListener {
        fun onCancelled(view: PokerCardView)
    }

    interface OnClickListener {
        fun onClickedWhileSelecting(view: PokerCardView)
    }
}