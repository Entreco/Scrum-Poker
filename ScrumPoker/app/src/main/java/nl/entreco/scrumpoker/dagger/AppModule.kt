package nl.entreco.scrumpoker.dagger

import dagger.Module
import dagger.Provides
import nl.entreco.scrumpoker.PokerApp
import nl.entreco.scrumpoker.libcore.dagger.scopes.AppScope

@Module
class AppModule(private val application: PokerApp) {

    @Provides
    @AppScope
    fun provideApplication(): PokerApp {
        return application
    }
}