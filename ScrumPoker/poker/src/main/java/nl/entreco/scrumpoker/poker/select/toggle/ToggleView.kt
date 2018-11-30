package nl.entreco.scrumpoker.poker.select.toggle

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import androidx.databinding.Bindable
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import nl.entreco.scrumpoker.poker.R
import nl.entreco.scrumpoker.poker.databinding.WidgetToggleBinding
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.math.max
import kotlin.math.min


class ToggleView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), View.OnTouchListener, Observable {

    private val inflater by lazy { LayoutInflater.from(context) }
    private val binding = DataBindingUtil.inflate<WidgetToggleBinding>(inflater, R.layout.widget_toggle, this, true)
    private val gestureDetector = GestureDetector(context, SingleTapConfirm())
    private val checked = AtomicBoolean(false)

    @Bindable
    var onCheckChangedListener: OnCheckChangedListener? = null

    @Bindable
    var onProgressChangedListener: OnProgessChangedListener? = null

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

    init {
        binding.thumb.setOnTouchListener(this)
    }

    private var isDragging = false
    private var deltaX = 0F
    private var lastX = 0F
    private var percentage = 0F
    private var maximumWidth = 0F

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        maximumWidth = w - binding.thumb.measuredWidth.toFloat()
        Log.v("[ToggleView] onSizeChanged maxWidth", maximumWidth.toString())
    }

    override fun onTouch(arg0: View, arg1: MotionEvent): Boolean {

        if (gestureDetector.onTouchEvent(arg1)) {
            val perc = binding.thumb.translationX / maximumWidth * 100
            animateTo(100 - perc)
            return true
        } else {

            val action = arg1.action
            if (action == MotionEvent.ACTION_DOWN && !isDragging) {
                isDragging = true
                deltaX = arg1.x
                return true
            } else if (isDragging) {
                when (action) {
                    MotionEvent.ACTION_MOVE -> {
                        val newX = min(maximumWidth, max(0F, arg0.x + arg1.x - deltaX))
                        percentage = newX / maximumWidth * 100F
                        arg0.x = newX
                        moveTo(percentage)
                        return true
                    }
                    MotionEvent.ACTION_UP -> {
                        isDragging = false
                        lastX = arg1.x
                        animateTo(percentage)
                        return true
                    }
                    MotionEvent.ACTION_CANCEL -> {
                        isDragging = false
                        arg0.x = lastX
                        animateTo(percentage)
                        return true
                    }
                }
            }
            return false
        }

    }

    private fun moveTo(percentage: Float) {
        binding.thumb.setImageLevel((percentage / 10).toInt())
        onProgressChangedListener?.onChanged(this, percentage / 100)
    }

    private fun animateTo(percentage: Float) {
        val new = percentage >= 50.0F
        binding.thumb.animate()
            .translationX(if (new) maximumWidth else 0F)
            .setUpdateListener {
                val percentage = (binding.thumb.translationX / maximumWidth)
                binding.thumb.setImageLevel((percentage * 10).toInt())
                onProgressChangedListener?.onChanged(this, percentage)
            }
            .start()

        val prev = checked.getAndSet(new)
        if (prev != new) {
            onCheckChangedListener?.onChanged(this, new)
        }
    }

    private class SingleTapConfirm : GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            return true
        }
    }
}