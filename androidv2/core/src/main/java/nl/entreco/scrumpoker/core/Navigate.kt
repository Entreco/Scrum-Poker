package nl.entreco.scrumpoker.core

import android.content.Intent

private const val PACKAGE_NAME = "nl.entreco.scrumpoker"

object Navigate {
    fun to(namedActivity: NamedActivity): Intent {
        return Intent(Intent.ACTION_VIEW).setClassName(
            PACKAGE_NAME,
            namedActivity.className
        )
    }
}

interface NamedActivity {
    /**
     * The activity class name.
     */
    val className: String
}

object Activities {

    /**
     * AboutActivity
     */
    object About : NamedActivity {
        override val className = "$PACKAGE_NAME.about.AboutActivity"
    }
}