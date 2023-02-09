package me.nickp0is0n.escapefromthicgrim.models.field.perks

import me.nickp0is0n.escapefromthicgrim.models.PlayerProperty

class LargeDamageBonusPerk: CellPerk {
    override fun getPropertyChange(): Pair<PlayerProperty, Int> {
        return Pair(PlayerProperty.DAMAGE, +30)
    }

    override fun getPerkUIName(): String = "Power of wizards"
}