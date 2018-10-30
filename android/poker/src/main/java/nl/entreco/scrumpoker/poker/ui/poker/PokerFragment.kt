package nl.entreco.scrumpoker.poker.ui.poker

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import nl.entreco.scrumpoker.poker.R

class PokerFragment : Fragment() {

    companion object {
        fun newInstance() = PokerFragment()
    }

    private lateinit var viewModel: PokerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_poker, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PokerViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
