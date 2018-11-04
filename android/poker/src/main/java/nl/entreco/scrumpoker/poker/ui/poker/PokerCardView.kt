package nl.entreco.scrumpoker.poker.ui.poker

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import nl.entreco.scrumpoker.poker.R
import nl.entreco.scrumpoker.poker.databinding.PokerCardBinding
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.math.atan2
import kotlin.random.Random

@SuppressLint("ViewConstructor")
class PokerCardView constructor(
    context: Context,
    val card: PokerCardModel,
    private val layoutManager: CardLayoutManager
) : ConstraintLayout(context) {

    private lateinit var binding: PokerCardBinding

    fun setState(state: CardState){
        layoutManager.setState(this, state)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        val lp = FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT, Gravity.CENTER)
        layoutParams = lp
        clipToPadding = false
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.poker_card, this, true
        )
        binding.card = card
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = binding.root.measuredWidth + binding.root.paddingLeft + binding.root.paddingRight
        val height = binding.root.measuredHeight + binding.root.paddingTop + binding.root.paddingBottom
        setMeasuredDimension(width, height)
    }

    override fun performClick(): Boolean {
        return super.performClick()
    }

    fun flip() {
        binding.cardFlipper.showNext()
    }
}