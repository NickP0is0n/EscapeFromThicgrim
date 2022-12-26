package me.nickp0is0n.escapefromthicgrim.models.field.perks

import me.nickp0is0n.escapefromthicgrim.models.PlayerProperty

class SmallArmorBonusPerk: CellPerk {
    override fun getPropertyChange(): Pair<PlayerProperty, Int> {
        return Pair(PlayerProperty.ARMOR, +15)
    }

    override fun getPerkUIName(): String = "Small steel-skin(tm) patented(c) potion"
}