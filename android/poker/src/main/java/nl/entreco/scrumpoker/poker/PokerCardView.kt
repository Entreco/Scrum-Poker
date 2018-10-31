package nl.entreco.scrumpoker.poker

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat.animate
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.poker_card.view.*
import nl.entreco.scrumpoker.poker.databinding.PokerCardBinding
import nl.entreco.scrumpoker.poker.ui.poker.PokerCardModel
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.math.atan2
import kotlin.random.Random

@SuppressLint("ViewConstructor")
class PokerCardView constructor(
    context: Context,
    val card: PokerCardModel,
    private val dragListener: OnDragListener
) : ConstraintLayout(context), View.OnTouchListener {

    private var dX = 0F
    private var dY = 0F
    private var startClick: Long = 0
    private val front = AtomicBoolean(true)
    private lateinit var binding: PokerCardBinding

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        val lp = FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT, Gravity.CENTER)
        layoutParams = lp
        clipToPadding = false
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.poker_card, this, true)
        binding.card = card
        binding.cardFlipper.inAnimation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in)
//                    binding.cardFlipper.outAnimation = AnimationUtils.loadAnimation(context, android.R.anim.fade_out)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = binding.root.measuredWidth + binding.root.paddingLeft + binding.root.paddingRight
        val height = binding.root.measuredHeight + binding.root.paddingTop + binding.root.paddingBottom
        setMeasuredDimension(width, height)
    }

    fun setOrder(index: Int, total: Int) {

//        if (index == 0) {
//            animate()
//
//                .rotationX(0F).rotationY(0F)
//                .setDuration(300)
//                .start()
//        } else {

            animate()
                .rotation(if(index == total) Random.nextInt(-10, 10).toFloat() else 0F)
                .translationX(Random.nextInt(-10, 10).toFloat())
                .translationY(Random.nextInt(-10, 10).toFloat())
                .translationZ(index.toFloat())
                .rotationX(0F).rotationY(0F)
                .setDuration(300)
                .start()
//        }

        setOnTouchListener(if (index == total) this else null)
    }

    override fun onTouch(view: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startClick = System.currentTimeMillis()
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

                if (System.currentTimeMillis() - startClick < 100 && dX < 50 && dY < 50) {
                    dragListener.onCancelled(this)
                    dragListener.onSelected(this)
                } else {

                    val newX = event.rawX + dX
                    val newY = event.rawY + dY
                    val triggered = newX < -200 || newX > 600

                    if (triggered) {
                        view.animate()
                            .scaleX(1F)
                            .scaleY(1F)
                            .rotationXBy(if (newY < 0) 20F else -20F)
                            .rotationYBy(if (newX < 0) 20F else -20F)
                            .translationZ(0F)
                            .setDuration(0)
                            .start()

                        if(binding.cardFlipper.displayedChild != 0) {
                            binding.cardFlipper.displayedChild = 0
                        }
                        dragListener.onThrownAway(this)
                    } else {
                        dragListener.onCancelled(this)
                    }
                }
            }
        }

        return true
    }

    fun flip() {
        front.set(!front.get())

        animate()
            .scaleX(2F)
            .scaleY(2F)
            .rotationX(0F)
            .rotation(0F)
            .setDuration(500)
            .start()

        if(front.get()) {
            binding.cardFlipper.showNext()
        } else {
            animate()
                .setDuration(250)
                .rotationY(-90F)
                .withEndAction {

                    binding.cardFlipper.showNext()
                    animate()
                        .setDuration(250)
                        .rotationY(-180F)
                        .withEndAction{
                            requestLayout()
                        }
                        .start()
                }
                .start()
        }
    }

    interface OnDragListener {
        fun onThrownAway(view: PokerCardView)
        fun onCancelled(view: PokerCardView)
        fun onSelected(view: PokerCardView)
    }
}