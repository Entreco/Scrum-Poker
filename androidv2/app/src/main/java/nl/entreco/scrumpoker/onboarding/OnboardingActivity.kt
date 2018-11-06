package nl.entreco.scrumpoker.onboarding

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import nl.entreco.scrumpoker.R
import nl.entreco.scrumpoker.core.Activities
import nl.entreco.scrumpoker.core.BaseActivity
import nl.entreco.scrumpoker.core.Navigate


class OnboardingActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
    }

    fun launchAbout(view: View){
        Toast.makeText(this, "Yohoo", Toast.LENGTH_SHORT).show()
        startActivity(Navigate.to(Activities.About),
            ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
    }

    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, OnboardingActivity::class.java))
        }
    }
}