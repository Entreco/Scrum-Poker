package nl.entreco.scrumpoker.poker.ui.poker

interface CardLayoutManager {

    fun setState(view: PokerCardView, state: CardState)
    fun add(cardView: PokerCardView, index: Int, total: Int)
}