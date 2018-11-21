package nl.entreco.scrumpoker.poker.select.toggle

import android.view.View

interface OnCheckChangedListener {
    fun onChanged(view: View, checked: Boolean)
}