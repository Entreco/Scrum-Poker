package nl.entreco.scrumpoker

import android.app.Application
import nl.entreco.scrumpoker.dagger.AppModule
import nl.entreco.scrumpoker.dagger.DaggerAppComponent
import nl.entreco.scrumpoker.libcore.dagger.DaggerCoreComponent

class PokerApp : Application() {

    private val coreComponent by lazy {
        DaggerCoreComponent.builder()
            .build()
    }

    val appComponent by lazy {
        DaggerAppComponent.builder()
            .coreComponent(coreComponent)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}