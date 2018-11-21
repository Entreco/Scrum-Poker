package nl.entreco.scrumpoker.poker.model

class Deck(initial: List<Card>) {

    private val cards = mutableListOf<Card>().apply {
        addAll(initial)
    }

    fun get() : List<Card>{
        return cards.toList()
    }

    fun shuffle(): List<Card> {
        return cards.shuffled()
    }
}