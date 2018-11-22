package nl.entreco.scrumpoker.poker.select.stack

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.core.view.ViewCompat


class StackLayoutManager(context: Context?, orientation: Int, reverseLayout: Boolean) :
    LinearLayoutManager(context, orientation, reverseLayout) {

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    override fun canScrollHorizontally() = false
    override fun canScrollVertically() = false

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        super.onLayoutChildren(recycler, state)
        updatePosition()
    }

    private fun updatePosition() {
        val childCount = childCount
        for (i in 0 until childCount) {
            getChildAt(i)?.let { view ->
                view.translationY = -view.top.toFloat()
                view.elevation = (childCount - i) * 2F
                view.rotation = (Math.random().toFloat() * 10) - 5
            }
        }
    }
}