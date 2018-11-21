package nl.entreco.scrumpoker

import android.content.Intent
import android.os.Bundle

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(Intent.ACTION_VIEW).setClassName(this, "nl.entreco.scrumpoker.poker.PokerActivity")
        startActivity(intent)
        finish()
    }

}
