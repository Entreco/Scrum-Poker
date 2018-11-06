package nl.entreco.scrumpoker.splash

import android.os.Bundle
import android.os.SystemClock
import nl.entreco.scrumpoker.onboarding.OnboardingActivity
import nl.entreco.scrumpoker.core.BaseActivity

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SystemClock.sleep(500)
        OnboardingActivity.launch(this)
        finish()
    }
}
