package nl.entreco.scrumpoker.poker.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.databinding.Bindable
import nl.entreco.scrumpoker.poker.ui.poker.*
import nl.entreco.scrumpoker.poker.ui.poker.stack.StackAnimator
import nl.entreco.scrumpoker.poker.ui.poker.stack.StackClicker

class PokerStackedView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var centerX = 0F
    private var centerY = 0F
    private val centerPaint = Paint()

    init {
        centerPaint.style = Paint.Style.FILL
        centerPaint.color = Color.parseColor("#FF0000")
    }

    private val layoutManager: CardLayoutManager

    init {
        layoutManager = StackLayoutManager()
    }

    @Bindable
    fun setCards(cards: List<PokerCardModel>) {
        cards.forEachIndexed { index, card ->
            val pokerCardView = PokerCardView(context, card, layoutManager)
            addView(pokerCardView)
            layoutManager.add(pokerCardView, index, cards.size - 1)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = w / 2.toFloat()
        centerY = h / 2.toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle(centerX, centerY, 200F, centerPaint)
    }
}