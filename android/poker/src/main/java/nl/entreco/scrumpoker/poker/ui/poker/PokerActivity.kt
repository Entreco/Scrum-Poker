package nl.entreco.scrumpoker.poker.ui.poker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import nl.entreco.scrumpoker.poker.R

class PokerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poker)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PokerFragment.newInstance())
                .commitNow()
        }
    }

}
