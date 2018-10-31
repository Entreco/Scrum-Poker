package nl.entreco.scrumpoker.poker.ui.poker

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import nl.entreco.scrumpoker.poker.model.PokerCard

class PokerViewModel : ViewModel() {
    val cards = ObservableArrayList<PokerCardModel>()

    init {
        cards.add(PokerCardModel(PokerCard(0, "0")))
        cards.add(PokerCardModel(PokerCard(1, "1")))
        cards.add(PokerCardModel(PokerCard(2, "2")))
        cards.add(PokerCardModel(PokerCard(3, "3")))
        cards.add(PokerCardModel(PokerCard(4, "5")))
        cards.add(PokerCardModel(PokerCard(5, "8")))
        cards.add(PokerCardModel(PokerCard(5, "8")))
    }
}
