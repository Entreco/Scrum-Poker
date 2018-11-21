package nl.entreco.scrumpoker.poker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nl.entreco.scrumpoker.poker.model.Card
import nl.entreco.scrumpoker.poker.model.Deck

class PokerViewModel : ViewModel() {

    private val items = MutableLiveData<List<Card>>()
    private val deck =
        Deck(
            listOf(
                Card(0, "0"),
                Card(1, "½"),
                Card(2, "1"),
                Card(3, "2"),
                Card(4, "3"),
                Card(5, "5"),
                Card(6, "8"),
                Card(7, "13"),
                Card(8, "20"),
                Card(9, "40"),
                Card(10, "100"),
                Card(11, "∞"),
                Card(12, "??"),
                Card(13, "☕"),
                Card(14, "\uD83D\uDEBB")
            )
        )

    init {
        items.postValue(deck.get())
    }

    fun toggle(isChecked: Boolean){
        items.postValue(deck.shuffle())
    }

    fun reset(){
        items.postValue(deck.get())
    }


    fun cards(): LiveData<List<Card>> {
        return items
    }
}