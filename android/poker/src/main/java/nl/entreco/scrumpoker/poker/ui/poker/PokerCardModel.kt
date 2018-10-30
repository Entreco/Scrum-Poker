package nl.entreco.scrumpoker.poker.ui.poker

import androidx.databinding.ObservableField
import nl.entreco.scrumpoker.poker.model.PokerCard

data class PokerCardModel(private val pokerCard: PokerCard){
    val value = ObservableField<String>(pokerCard.value)
}