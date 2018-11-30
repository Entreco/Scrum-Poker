package nl.entreco.scrumpoker.poker.dagger

import nl.entreco.scrumpoker.BaseActivity
import nl.entreco.scrumpoker.PokerApp
import nl.entreco.scrumpoker.dagger.ActivityModule
import nl.entreco.scrumpoker.dagger.DaggerActivityComponent

inline fun <reified Feature> BaseActivity.module(
    mode: LazyThreadSafetyMode = LazyThreadSafetyMode.NONE,
    crossinline provider: (PokerFeature) -> Feature
) = lazy(mode) {

    val app: PokerApp = application as PokerApp
    val activityComponent = DaggerActivityComponent
        .builder()
        .activityModule(ActivityModule(this))
        .build()

    val feature = DaggerPokerFeature.builder()
        .appComponent(app.appComponent)
        .activityComponent(activityComponent)
        .build()

    provider(feature)
}