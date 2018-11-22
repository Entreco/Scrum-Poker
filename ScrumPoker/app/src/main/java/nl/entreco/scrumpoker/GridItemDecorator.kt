package nl.entreco.scrumpoker

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

class GridItemDecorator(private val offsetInPixels: Int) : RecyclerView.ItemDecoration() {
    constructor(context: Context, @DimenRes resId: Int) : this(context.resources.getDimensionPixelSize(resId))

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(offsetInPixels, offsetInPixels, offsetInPixels, offsetInPixels)
    }
}