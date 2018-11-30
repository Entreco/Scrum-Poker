package nl.entreco.scrumpoker.dagger

import android.content.Context
import android.os.Handler
import dagger.Component

@Component(modules = [(ActivityModule::class)])
interface ActivityComponent {

    // Expose @Provided classes here
    fun handler() : Handler
    fun context() : Context
}