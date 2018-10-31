package nl.entreco.scrumpoker.poker.ui.poker

interface CardAnimator {
    fun render(card: PokerCardView, state: CardState)
}