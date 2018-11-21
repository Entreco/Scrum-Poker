package nl.entreco.scrumpoker.poker.select

import androidx.databinding.ObservableInt
import nl.entreco.scrumpoker.poker.model.Card

class CardView(
    card: Card
) {
    val cardNumber = ObservableInt(card.value)
}