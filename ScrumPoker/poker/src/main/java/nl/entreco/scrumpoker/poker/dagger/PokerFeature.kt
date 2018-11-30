package nl.entreco.scrumpoker.poker.dagger

import dagger.Component
import nl.entreco.scrumpoker.BaseActivity
import nl.entreco.scrumpoker.dagger.ActivityComponent
import nl.entreco.scrumpoker.dagger.AppComponent
import nl.entreco.scrumpoker.libcore.dagger.FeatureComponent
import nl.entreco.scrumpoker.libcore.dagger.scopes.FeatureScope

@FeatureScope
@Component(dependencies = [AppComponent::class, ActivityComponent::class])
interface PokerFeature : FeatureComponent<BaseActivity> {
    fun plus(module: PokerModule): PokerComponent
}