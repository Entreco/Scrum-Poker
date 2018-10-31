package nl.entreco.scrumpoker.poker.ui.poker

interface CardClicker {
    fun prepare(card: PokerCardView, state: CardState)
}