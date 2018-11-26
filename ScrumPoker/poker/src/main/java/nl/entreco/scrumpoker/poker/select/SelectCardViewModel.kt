package nl.entreco.scrumpoker.poker.select

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nl.entreco.scrumpoker.poker.R
import nl.entreco.scrumpoker.poker.model.Card
import nl.entreco.scrumpoker.poker.model.Deck
import java.util.concurrent.atomic.AtomicBoolean

class SelectCardViewModel : ViewModel() {

    private val toggled = MutableLiveData<Boolean>()
    private val items = MutableLiveData<List<Card>>()
    private val deck =
        Deck(
            listOf(
                Card(0, R.string.card_0),
                Card(1, R.string.card_1),
                Card(2, R.string.card_2),
                Card(3, R.string.card_3),
                Card(4, R.string.card_4),
                Card(5, R.string.card_5),
                Card(6, R.string.card_6),
                Card(7, R.string.card_7),
                Card(8, R.string.card_8),
                Card(9, R.string.card_9),
                Card(10, R.string.card_10),
                Card(11, R.string.card_11),
                Card(12, R.string.card_12),
                Card(13, R.string.card_13),
                Card(14, R.string.card_14)
            )
        )

    init {
        items.postValue(deck.get())
        toggled.postValue(false)
    }

    fun toggle(isChecked: Boolean){
        items.postValue(deck.shuffle())
        toggled.postValue(isChecked)
    }

    fun slide(view: View, progress: Float){
        Log.i("REMCO", "slide: $progress")
    }

    fun reset(){
        items.postValue(deck.get())
    }

    fun toggle(): LiveData<Boolean>{
        return toggled
    }

    fun cards(): LiveData<List<Card>> {
        return items
    }
}