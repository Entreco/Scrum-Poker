package nl.entreco.scrumpoker.poker

data class Deck(private val cards: List<Card>){
    fun shuffle() : List<Card> {
        return cards.shuffled()
    }
}