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
            is CardState.Hiding -> setupHideClicker(card)
            is CardState.Showing -> setupShowClicker(card)
        }
    }

    private fun setupStackClicker(view: PokerCardView,index: Int, total: Int) {
        view.setOnTouchListener(if (index == total) StackToucher(view, dragListener, clickListener, skipListeners) else null)
    }

    private fun setupHideClicker(view: PokerCardView){
        view.setOnTouchListener(null)
        view.setOnClickListener(HideClicker(view, clickListener))
    }

    private fun setupShowClicker(view: PokerCardView){
        view.setOnTouchListener(null)
        view.setOnClickListener(ShowClicker(view, clickListener))
    }

    interface OnSkipListener {
        fun onThrownAway(view: PokerCardView)
    }

    interface OnDragListener {
        fun onCancelled(view: PokerCardView)
    }

    interface OnClickListener {
        fun onClickedWhileSelecting(view: PokerCardView)
        fun onClickedWhileHiding(view: PokerCardView)
        fun onClickedWhileShowing(view: PokerCardView)
    }
}