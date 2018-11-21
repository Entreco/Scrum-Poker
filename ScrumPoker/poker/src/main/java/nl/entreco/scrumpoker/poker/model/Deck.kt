package nl.entreco.scrumpoker.poker

class Deck(initial: List<Card>) {

    private val cards = mutableListOf<Card>().apply {
        addAll(initial)
    }

    fun shuffle(): List<Card> {
        return cards.shuffled()
    }

    fun roll(): List<Card> {
        cards.add(0, cards.last())
        cards.removeAt(cards.size - 1)
        return cards
    }
}