package nl.entreco.scrumpoker.poker

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PokerViewModel : ViewModel() {

    private val items = MutableLiveData<List<Card>>()
    private val deck =
        Deck(
            listOf(
                Card(0, "0"),
                Card(1, "1/2"),
                Card(2, "1"),
                Card(3, "2"),
                Card(4, "3"),
                Card(5, "5"),
                Card(6, "8"),
                Card(7, "13")
            )
        )

    init {
        shuffle()
    }

    fun deck() : List<Card> {
        return deck.shuffle()
    }

    fun shuffle() {
        items.postValue(deck.shuffle())
    }

    fun cards() : LiveData<List<Card>> {
        return items
    }
}