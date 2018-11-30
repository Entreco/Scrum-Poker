package nl.entreco.scrumpoker.dagger

import android.content.Context
import android.os.Handler
import dagger.Module
import dagger.Provides
import nl.entreco.scrumpoker.BaseActivity

@Module
class ActivityModule(private val activity: BaseActivity){

    @Provides
    fun provideHandler(): Handler {
        return Handler()
    }

    @Provides
    fun provideContext(): Context {
        return activity
    }
}