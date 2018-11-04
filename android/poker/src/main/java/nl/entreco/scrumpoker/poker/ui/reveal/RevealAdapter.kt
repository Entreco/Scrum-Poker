package nl.entreco.scrumpoker.poker.ui.reveal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import nl.entreco.scrumpoker.poker.R
import nl.entreco.scrumpoker.poker.databinding.PokerFrontBinding

class RevealAdapter(private val number: String, private val reveal: (View)-> Unit, private val dismiss: (View)->Unit) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val card = when (position) {
            0 -> showBack(container)
            else -> showFront(container)
        }
        container.addView(card)
        return card
    }

    private fun showBack(container: ViewGroup): View {
        val view = LayoutInflater.from(container.context).inflate(R.layout.poker_back, container, false)
        view.setOnClickListener {
            reveal(it)
        }
        return view
    }

    private fun showFront(container: ViewGroup): View {
        val binding = DataBindingUtil.inflate<PokerFrontBinding>(
            LayoutInflater.from(container.context),
            R.layout.poker_front,
            container,
            false
        )
        binding.value = number
        binding.root.setOnClickListener {
            dismiss(it)
        }
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as? View)
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun getCount(): Int {
        return 2
    }
}