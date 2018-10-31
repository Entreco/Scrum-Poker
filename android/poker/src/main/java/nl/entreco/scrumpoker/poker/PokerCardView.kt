package nl.entreco.scrumpoker.poker

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import nl.entreco.scrumpoker.poker.databinding.PokerCardBinding
import nl.entreco.scrumpoker.poker.ui.poker.PokerCardModel
import kotlin.math.atan2
import kotlin.random.Random

@SuppressLint("ViewConstructor")
class PokerCardView constructor(
    context: Context,
    private val card: PokerCardModel,
    private val dragListener: OnDragListener
) : ConstraintLayout(context), View.OnTouchListener {

    private var dX = 0F
    private var dY = 0F
    private var indexInStack = 0
    private var stackSize = 0
    private lateinit var binding: PokerCardBinding

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        val lp = FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT, Gravity.CENTER)
        lp.setMargins(8, 8, 24, 24)
        layoutParams = lp
        clipToPadding = false
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.poker_card, this, true)
        binding.card = card
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = binding.root.measuredWidth + binding.root.paddingLeft + binding.root.paddingRight + 80
        val height = binding.root.measuredHeight + binding.root.paddingTop + binding.root.paddingBottom + 80
        setMeasuredDimension(width, height)
    }

    fun setOrder(index: Int, total: Int) {
        indexInStack = index
        stackSize = total

        if (index == 0) {
            animate()
                .rotation(Random.nextInt(-10, 10).toFloat())
                .setDuration(300)
                .start()
        }

        animate()
            .translationX(Random.nextInt(-10, 10).toFloat())
            .translationY(Random.nextInt(-10, 10).toFloat())
            .translationZ(index.toFloat())
            .rotationX(0F).rotationY(0F)
            .setDuration(300)
            .start()

        setOnTouchListener(if (index == total) this else null)
    }

    override fun onTouch(view: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                dX = view.x - event.rawX
                dY = view.y - event.rawY
            }

            MotionEvent.ACTION_MOVE -> {
                val newX = event.rawX + dX
                val newY = event.rawY + dY

                val rotation = (Math.toDegrees(atan2(newY, newX).toDouble()).toFloat() - 90) / 2
                val rotationX = (newY - 750) / 1500 * 10
                val rotationY = (newX - 500) / 1000 * 10
                Log.i("WOOOO", "newX:$newX newY:$newY")
                Log.i("WOOOO", "rotation:$rotation")
                Log.i("WOOOO", "rotationX:$rotationX")
                Log.i("WOOOO", "rotationY:$rotationY")

                view.animate()
                    .x(newX)
                    .y(newY)
                    .rotationX(rotationX)
                    .rotationY(rotationY)
                    .rotationBy(rotation / 36)
                    .setDuration(0)
                    .start()
            }

            MotionEvent.ACTION_UP -> {

                val newX = event.rawX + dX
                val newY = event.rawY + dY
                val triggered = newX < -200 || newX > 600

                if (triggered) {
                    view.animate()
                        .rotationXBy(if (newY < 0) 20F else -20F)
                        .rotationYBy(if (newX < 0) 20F else -20F)
                        .translationZ(0F)
                        .setDuration(0)
                        .start()
                    dragListener.onThrownAway(this)
                } else {
                    dragListener.onCancelled(this)
                }
            }
        }

        return true
    }

    interface OnDragListener {
        fun onThrownAway(view: PokerCardView)
        fun onCancelled(view: PokerCardView)
    }
}