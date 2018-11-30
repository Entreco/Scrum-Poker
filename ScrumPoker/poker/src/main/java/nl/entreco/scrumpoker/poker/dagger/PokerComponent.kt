package nl.entreco.scrumpoker.poker.dagger

import dagger.Subcomponent
import nl.entreco.scrumpoker.poker.select.CardsAdapter
import nl.entreco.scrumpoker.poker.select.SelectCardViewModel

@PokerScope
@Subcomponent(modules = [PokerModule::class])
interface PokerComponent {
    fun viewModel(): SelectCardViewModel
    fun adapter(): CardsAdapter
}