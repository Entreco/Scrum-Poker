package nl.entreco.scrumpoker.dagger

import dagger.Component
import nl.entreco.scrumpoker.PokerApp
import nl.entreco.scrumpoker.libcore.dagger.CoreComponent
import nl.entreco.scrumpoker.libcore.dagger.scopes.AppScope

@AppScope
@Component(modules = [(AppModule::class)], dependencies = [(CoreComponent::class)])
interface AppComponent {
    fun inject(app: PokerApp)
}