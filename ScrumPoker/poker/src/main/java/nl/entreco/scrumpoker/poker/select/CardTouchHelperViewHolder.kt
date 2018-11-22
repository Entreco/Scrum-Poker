package nl.entreco.scrumpoker.poker.select


interface CardTouchHelperViewHolder {
    /**
     * Called when the [CardTouchHelperAdapter] first registers an item as being moved or swiped.
     * Implementations should update the item view to indicate it's active state.
     */
    fun onItemSelected()


    /**
     * Called when the [CardTouchHelperAdapter] has completed the move or swipe, and the active item
     * state should be cleared.
     */
    fun onItemClear()
}