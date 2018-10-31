package nl.entreco.scrumpoker.poker

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import nl.entreco.scrumpoker.poker.databinding.PokerCardBinding
import nl.entreco.scrumpoker.poker.ui.poker.PokerCardModel
import kotlin.random.Random

@SuppressLint("ViewConstructor")
class PokerCardView constructor(
    context: Context,
    private val card: PokerCardModel,
    private val dragListener: OnDragListener
) : FrameLayout(context), View.OnTouchListener {

    private var dX = 0F
    private var dY = 0F
    private var indexInStack = 0
    private var stackSize = 0
    private lateinit var binding : PokerCardBinding

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        val lp = FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT, Gravity.CENTER)
        lp.setMargins(120,120,120,120)
        layoutParams = lp
        clipToPadding = false
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.poker_card, this, true)
        binding.card = card
    }

//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//        val width = binding.root.measuredWidth + binding.root.paddingLeft + binding.root.paddingRight + 80
//        val height = binding.root.measuredHeight + binding.root.paddingTop + binding.root.paddingBottom + 80
//        setMeasuredDimension(width, height)
//    }

    fun setOrder(index: Int, total: Int){
        indexInStack = index
        stackSize = total
        animate().rotation(Random.nextInt(-10, 10).toFloat()).setDuration(300).start()
        setOnTouchListener(if(index == total) this else null)
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

                view.animate().x(newX).y(newY).setDuration(0).start()
            }

            MotionEvent.ACTION_UP -> {
                dragListener.onRelease(this)
            }
        }

        return true
    }

    interface OnDragListener {
        fun onRelease(view: PokerCardView)
    }
}