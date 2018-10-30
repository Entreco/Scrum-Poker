package nl.entreco.scrumpoker.poker.ui.poker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import nl.entreco.scrumpoker.poker.R
import nl.entreco.scrumpoker.poker.databinding.FragmentPokerBinding

class PokerFragment : Fragment() {

    private lateinit var binding: FragmentPokerBinding
    private val viewModel: PokerViewModel by lazy { ViewModelProviders.of(this).get(PokerViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_poker, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    companion object {
        fun newInstance() = PokerFragment()
    }
}
