package nl.entreco.scrumpoker.poker.ui.reveal

import android.view.View
import androidx.viewpager.widget.ViewPager
import kotlin.math.abs


class RevealTransformer : ViewPager.PageTransformer {

    override fun transformPage(page: View, position: Float) {
        page.translationX = -position * page.width
        page.cameraDistance = 12000F

        if (position < 0.5 && position > -0.5) {
            page.visibility = View.VISIBLE
        } else {
            page.visibility = View.INVISIBLE
        }

        when {
            position < -1 -> // [-Infinity,-1)
                page.alpha = 0F
            position <= 0 -> {    // [-1,0]
                page.alpha = 1F
                page.rotationY = 180 * (1 - abs(position) + 1)
            }
            position <= 1 -> {    // (0,1]
                page.alpha = 1F
                page.rotationY = -180 * (1 - abs(position) + 1)
            }
            else -> // (1,+Infinity]
                page.alpha = 0F
        }
    }
}