package nl.entreco.scrumpoker.poker.ui.poker

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import nl.entreco.scrumpoker.poker.model.PokerCard

class PokerViewModel : ViewModel() {
    val cards = ObservableArrayList<PokerCardModel>()

    init {
        cards.add(PokerCardModel(PokerCard(1, "yolo")))
    }
}
