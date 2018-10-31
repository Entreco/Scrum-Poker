package nl.entreco.scrumpoker.poker.ui.poker.stack

import android.util.Log
import android.view.MotionEvent
import android.view.View
import nl.entreco.scrumpoker.poker.ui.poker.PokerCardView
import kotlin.math.atan2

class StackToucher(
    private val view: PokerCardView,
    private val dragListener: StackClicker.OnDragListener,
    private val clickListener: StackClicker.OnClickListener,
    private val skipListeners: List<StackClicker.OnSkipListener>
) : View.OnTouchListener {

    private var dX = 0F
    private var dY = 0F
    private var startClick: Long = 0

    override fun onTouch(v: View, event: MotionEvent): Boolean {
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
                    dragListener.onCancelled(view)
                    clickListener.onClickedWhileSelecting(view)
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

                        skipListeners.forEach {
                            it.onThrownAway(view) }
                    } else {
                        dragListener.onCancelled(view)
                    }
                }
            }
        }

        return true
    }
}