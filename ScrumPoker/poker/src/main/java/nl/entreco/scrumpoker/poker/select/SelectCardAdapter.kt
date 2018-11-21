package nl.entreco.scrumpoker.poker.select

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import nl.entreco.scrumpoker.poker.R
import nl.entreco.scrumpoker.poker.databinding.CardBinding
import nl.entreco.scrumpoker.poker.model.Card

class CardsAdapter : ListAdapter<Card, CardsAdapter.CardViewHolder>(differ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<CardBinding>(inflater,
            R.layout.card, parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CardViewHolder(private val binding: CardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(card: Card) {
            binding.card = CardView(card)
            binding.executePendingBindings()
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