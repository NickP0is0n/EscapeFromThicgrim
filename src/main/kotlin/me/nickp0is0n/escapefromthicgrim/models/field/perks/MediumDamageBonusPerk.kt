package me.nickp0is0n.escapefromthicgrim.models.field.perks

import me.nickp0is0n.escapefromthicgrim.models.PlayerProperty

class MediumDamageBonusPerk: CellPerk {
    override fun getPropertyChange(): Pair<PlayerProperty, Int> {
        return Pair(PlayerProperty.DAMAGE, +20)
    }

    override fun getPerkUIName(): String = "Potion of power"
}