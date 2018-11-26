package nl.entreco.scrumpoker.poker.select

import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import nl.entreco.scrumpoker.poker.databinding.CardBinding
import nl.entreco.scrumpoker.poker.model.Card
import nl.entreco.scrumpoker.poker.R
import java.util.*

class CardsAdapter : ListAdapter<Card, CardsAdapter.CardViewHolder>(differ), CardTouchHelperAdapter {

    var onDragListener: OnStartDragListener? = null
    private val items = mutableListOf<Card>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<CardBinding>(inflater, R.layout.card, parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(getItem(position))

        onDragListener?.let { listener ->
            // Start a drag whenever the handle view it touched
            if(position == items.size) {
                holder.itemView.setOnTouchListener { view, event ->
                    if (event.action == MotionEvent.ACTION_DOWN) {
                        listener.onStartDrag(holder)
                    }
                    false
                }
            } else {
                holder.itemView.setOnTouchListener(null)
            }
        }
    }

    override fun submitList(list: List<Card>?) {
        this.items.clear()
        this.items.addAll(list ?: emptyList())
        super.submitList(list)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        // Item Moved from -> to
//        Collections.swap(items, fromPosition, toPosition)
//        submitList(items.toList())
        onItemDismiss(fromPosition)
        return true;
    }

    override fun onItemDismiss(position: Int) {
        // Dismissed -> Release back to first position
        val dismissed = items.removeAt(position)
        submitList((items + dismissed).toList())
    }

    class CardViewHolder(private val binding: CardBinding) : RecyclerView.ViewHolder(binding.root), CardTouchHelperViewHolder {
        fun bind(card: Card) {
            binding.card = CardView(card)
            binding.executePendingBindings()
        }

        override fun onItemSelected() {
            // Card is Selected
        }

        override fun onItemClear() {
            // Card was Cleared -> calculate new position??
            Log.i("REMCO", "OnItemClear $this -> randomize ?? ")
        }
    }
}

internal val differ = object : DiffUtil.ItemCallback<Card>() {
    override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem.id == newItem.id
    }
}