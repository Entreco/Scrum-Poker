package nl.entreco.scrumpoker.poker

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import nl.entreco.scrumpoker.poker.R.drawable.card
import nl.entreco.scrumpoker.poker.databinding.PokerCardBinding
import nl.entreco.scrumpoker.poker.ui.poker.PokerCardModel

@SuppressLint("ViewConstructor")
class PokerCardView constructor(
    context: Context,
    card: PokerCardModel,
    parent: View
) : FrameLayout(context), View.OnTouchListener {

    private val binding = DataBindingUtil.inflate<PokerCardBinding>(LayoutInflater.from(context), R.layout.poker_card, this, true)
    private val springX = SpringAnimation(binding.root, DynamicAnimation.TRANSLATION_X, parent.width / 4.toFloat())
    private val springY = SpringAnimation(binding.root, DynamicAnimation.TRANSLATION_Y, parent.height / 4.toFloat())

    init {
        binding.card = card
        binding.root.setOnTouchListener(this)
        springExperiment()
    }

    private fun springExperiment() {

        springX.apply {
            //            velocityTracker.computeCurrentVelocity(1000)
            //            val velocity = velocityTracker.yVelocity
            setStartVelocity(0F)


            spring.dampingRatio = SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY
            spring.stiffness = SpringForce.STIFFNESS_LOW
            start()
        }

        springY.apply {
            setStartVelocity(0F)

            spring.dampingRatio = SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY
            spring.stiffness = SpringForce.STIFFNESS_LOW
            start()
        }

        // Converting dp per second to pixels per second
        //        val pixelPerSecond: Float = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpPerSecond, resources.displayMetrics)
    }

    //    val viewYDistance = ...
    var dX = 0F
    var dY = 0F
    override fun onTouch(view: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                dX = view.x - event.rawX
                dY = view.y - event.rawY
            }

            MotionEvent.ACTION_MOVE -> {
                val newX = event.rawX + dX
                val newY = event.rawY + dY

                view.animate().x(newX).y(newY).setDuration(0).start()
//                firstXAnim.animateToFinalPosition(newX)
//                firstYAnim.animateToFinalPosition(newY + viewYDistance)
            }

            MotionEvent.ACTION_UP -> {
                springExperiment()
            }
        }

        return true
    }
}