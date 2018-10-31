package nl.entreco.scrumpoker.poker.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.widget.FrameLayout
import androidx.databinding.Bindable
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import nl.entreco.scrumpoker.poker.PokerCardView
import nl.entreco.scrumpoker.poker.ui.poker.PokerCardModel

class PokerStackedView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), PokerCardView.OnDragListener {

    private var centerX = 0F
    private var centerY = 0F
    private val centerPaint = Paint()
    private val pokerCards = mutableListOf<PokerCardView>()

    init {
        centerPaint.style = Paint.Style.FILL
        centerPaint.color = Color.parseColor("#FF0000")
    }

    @Bindable
    fun setCards(cards: List<PokerCardModel>) {
        cards.forEachIndexed { index, card ->
            val pokerCardView = PokerCardView(context, card, this)
            pokerCardView.setOrder(index, cards.size - 1)
            addView(pokerCardView)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = w / 2.toFloat()
        centerY = h / 2.toFloat()
        Log.i("WOOOO", "centerX:$centerX")
        Log.i("WOOOO", "centerY:$centerY")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle(centerX, centerY, 200F, centerPaint)
    }

    override fun onRelease(view: PokerCardView) {

        pokerCards.remove(view)
        view.setOrder(0, pokerCards.size)
        pokerCards.add(0, view)

        val cardXposition = 0F //centerX - view.width
        val cardYposition = 0F //centerY - view.height



        SpringAnimation(view, DynamicAnimation.TRANSLATION_X, cardXposition).apply {
            //            velocityTracker.computeCurrentVelocity(1000)
            //            val velocity = velocityTracker.yVelocity
            setStartVelocity(0F)

            spring.dampingRatio = SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY
            spring.stiffness = SpringForce.STIFFNESS_LOW
            start()
        }

        SpringAnimation(view, DynamicAnimation.TRANSLATION_Y, cardYposition).apply {
            setStartVelocity(0F)

            spring.dampingRatio = SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY
            spring.stiffness = SpringForce.STIFFNESS_LOW
            start()
        }

        // Converting dp per second to pixels per second
        //        val pixelPerSecond: Float = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpPerSecond, resources.displayMetrics)
    }
}