package nl.entreco.scrumpoker.poker.select.stack

import android.graphics.Canvas
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import nl.entreco.scrumpoker.poker.select.CardTouchHelperAdapter
import androidx.recyclerview.widget.GridLayoutManager
import nl.entreco.scrumpoker.poker.select.CardTouchHelperViewHolder


class StackCardTouchHelperCallback(private val adapter: CardTouchHelperAdapter) : ItemTouchHelper.Callback() {

    override fun isItemViewSwipeEnabled() = true
    override fun isLongPressDragEnabled() = true


    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        // Set movement flags based on the layout manager
//        if (recyclerView.layoutManager is GridLayoutManager) {
//            val dragFlags = 0
//            val swipeFlags = 0
//            return ItemTouchHelper.Callback.makeMovementFlags(dragFlags, swipeFlags)
//        } else {
            val dragFlags = 0
            val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END or ItemTouchHelper.UP or ItemTouchHelper.DOWN
            return ItemTouchHelper.Callback.makeMovementFlags(dragFlags, swipeFlags)
//        }
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return if (viewHolder.itemViewType != target.itemViewType) {
            false
        } else {
            // Notify the adapter of the move
            adapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
            true
        }
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        // Notify the adapter of the dismissal
        adapter.onItemDismiss(viewHolder.adapterPosition);
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            // Fade out the view as it is swiped out of the parent's bounds
            viewHolder.itemView.translationX = dX
            viewHolder.itemView.translationY = dY
        } else {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        // We only want the active item to change
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if (viewHolder is CardTouchHelperViewHolder) {
                // Let the view holder know that this item is being moved or dragged
                viewHolder.onItemSelected()
            }
        }

        super.onSelectedChanged(viewHolder, actionState)
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        if (viewHolder is CardTouchHelperViewHolder) {
            // Tell the view holder it's time to restore the idle state
            viewHolder.onItemClear()
        }
    }
}