package me.nickp0is0n.escapefromthicgrim.models.field.perks

import me.nickp0is0n.escapefromthicgrim.models.PlayerProperty

interface CellPerk {
    fun getPropertyChange(): Pair<PlayerProperty, Int>
    fun getPerkUIName(): String
}