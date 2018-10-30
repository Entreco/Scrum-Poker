package nl.entreco.scrumpoker.poker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import nl.entreco.scrumpoker.poker.ui.poker.PokerFragment

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
