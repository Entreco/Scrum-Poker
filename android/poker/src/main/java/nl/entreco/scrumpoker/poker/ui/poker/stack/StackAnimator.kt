package nl.entreco.scrumpoker.poker.ui.poker.stack

import android.view.View
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import nl.entreco.scrumpoker.poker.ui.poker.CardAnimator
import nl.entreco.scrumpoker.poker.ui.poker.CardState
import nl.entreco.scrumpoker.poker.ui.poker.PokerCardView
import kotlin.random.Random

class StackAnimator : CardAnimator, StackClicker.OnDragListener, StackClicker.OnSkipListener {
    override fun render(card: PokerCardView, state: CardState) {
        when (state) {
            is CardState.Stacking -> renderStack(card, state.index, state.total)
            is CardState.Hiding -> renderAsHidden(card)
            is CardState.Showing -> renderAsShown(card)
            is CardState.Flinging -> renderAsFlung(card)
        }
    }

    private fun renderStack(card: PokerCardView, index: Int, total: Int) {
        val anim = card.animate()
            .scaleX(1F).scaleY(1F)
            .translationX(Random.nextInt(-10, 10).toFloat())
            .translationY(Random.nextInt(-10, 10).toFloat())
            .translationZ(index.toFloat())
            .rotationX(0F).rotationY(0F)


        if (index == 0 || card.rotation == 0F) {
            anim.rotation(Random.nextInt(-15, 5).toFloat())
        }

        anim
            .setDuration(300)
            .start()
    }

    private fun renderAsHidden(card: PokerCardView) {
        card.animate()
            .rotation(0F)
            .rotationX(0F)
            .scaleX(2F).scaleY(2F)
            .translationX(0F)
            .translationY(0F)
            .translationZ(20F)
            .setDuration(1000)
            .start()

        card.animate()
            .rotationY(-90F)
            .withEndAction { card.flip() }
            .setDuration(450)
            .start()

        card.animate()
            .setStartDelay(550)
            .rotationY(-180F)
            .withEndAction { card.flip() }
            .setDuration(500)
            .start()
    }

    private fun renderAsShown(card: PokerCardView) {
        card.animate()
            .rotation(0F)
            .rotationX(0F)
            .rotationY(0F)
            .scaleX(2F).scaleY(2F)
            .translationX(0F)
            .translationY(0F)
            .translationZ(20F)
            .withEndAction {
                card.flip()
            }
            .setDuration(0)
            .start()
    }

    private fun renderAsFlung(card: View) {
        card.animate().translationXBy(-1000F).rotationYBy(-10F).scaleX(1F).scaleY(1F).setDuration(0).start()
        releaseToCenter(card)
    }

    override fun onCancelled(view: PokerCardView) {
        releaseToCenter(view)
    }

    override fun onNextCard(view: PokerCardView) {
        releaseToCenter(view)
    }

    fun releaseToCenter(view: View, done:()->Unit = {}) {
        SpringAnimation(view, DynamicAnimation.TRANSLATION_X, 0F).apply {
            setStartVelocity(0F)

            spring.dampingRatio = SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY
            spring.stiffness = SpringForce.STIFFNESS_LOW
            addEndListener { animation, canceled, value, velocity ->
                done()
            }
            start()

        }

        SpringAnimation(view, DynamicAnimation.TRANSLATION_Y, 0F).apply {
            setStartVelocity(0F)

            spring.dampingRatio = SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY
            spring.stiffness = SpringForce.STIFFNESS_LOW
            start()
        }
    }
}