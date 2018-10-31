package nl.entreco.scrumpoker.poker.ui.poker

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import nl.entreco.scrumpoker.poker.model.PokerCard

class PokerViewModel : ViewModel() {
    val cards = ObservableArrayList<PokerCardModel>()

    init {
        cards.add(PokerCardModel(PokerCard(0, "0")))
        cards.add(PokerCardModel(PokerCard(1, "1/2")))
        cards.add(PokerCardModel(PokerCard(2, "1")))
        cards.add(PokerCardModel(PokerCard(3, "2")))
        cards.add(PokerCardModel(PokerCard(4, "3")))
        cards.add(PokerCardModel(PokerCard(5, "5")))
        cards.add(PokerCardModel(PokerCard(6, "8")))
        cards.add(PokerCardModel(PokerCard(7, "13")))
        cards.add(PokerCardModel(PokerCard(8, "20")))
        cards.add(PokerCardModel(PokerCard(9, "40")))
        cards.add(PokerCardModel(PokerCard(10, "100")))
        cards.add(PokerCardModel(PokerCard(11, "∞")))
        cards.add(PokerCardModel(PokerCard(12, "?")))
        cards.add(PokerCardModel(PokerCard(13, "coffee")))
    }
}
