package nl.entreco.scrumpoker.poker.ui

import android.view.View
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation

class CenterSpring(view: View) {
    val spring = SpringAnimation(view, DynamicAnimation.TRANSLATION_X, 0F)
}