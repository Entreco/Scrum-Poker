package nl.entreco.scrumpoker.poker.ui.poker

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.SharedElementCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import nl.entreco.scrumpoker.poker.R
import nl.entreco.scrumpoker.poker.databinding.FragmentPokerBinding
import nl.entreco.scrumpoker.poker.ui.reveal.RevealFragment

class PokerFragment : Fragment(), StackLayoutManager.OnCardClickListener {
    private lateinit var binding: FragmentPokerBinding

    private val viewModel: PokerViewModel by lazy { ViewModelProviders.of(this).get(PokerViewModel::class.java) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_poker, container, false)
        binding.viewModel = viewModel
        binding.pokerStackView.layoutManager = StackLayoutManager(this)

        return binding.root
    }

    override fun onClicked(view: PokerCardView) {
        val shared = view.findViewById<ConstraintLayout>(R.id.front)
        view.card.value.get()?.let { value ->
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.setReorderingAllowed(true)
                ?.addSharedElement(shared, shared.transitionName)
                ?.add(R.id.container, RevealFragment.newInstance(value), null)
                ?.commitNow()
        }
    }

    companion object {
        fun newInstance() = PokerFragment()
    }
}
