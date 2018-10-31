package nl.entreco.scrumpoker.poker.ui.poker

sealed class CardState {

    data class Stacking(val index: Int, val total: Int) : CardState()
    data class Hiding(val index: Int, val total: Int) : CardState()
    data class Showing(val index: Int, val total: Int) : CardState()
    data class Flinging(val index: Int = 0, val total: Int = 0) : CardState()
}