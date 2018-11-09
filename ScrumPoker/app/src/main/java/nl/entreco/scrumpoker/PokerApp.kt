package nl.entreco.scrumpoker

import android.app.Application
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module

class PokerApp : Application() {

    private val modules = listOf(module {})

    override fun onCreate() {
        super.onCreate()
        startKoin(this, modules)
    }
}