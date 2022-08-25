package me.nickp0is0n.escapefromthicgrim.models.field.perks

import me.nickp0is0n.escapefromthicgrim.models.PlayerProperty

class HealthBonusPerk: CellPerk {
    override fun getPropertyChange(): Pair<PlayerProperty, Int> {
        return Pair(PlayerProperty.HEALTH, +30)
    }

    override fun getPerkUIName(): String = "Healing Aura"
}