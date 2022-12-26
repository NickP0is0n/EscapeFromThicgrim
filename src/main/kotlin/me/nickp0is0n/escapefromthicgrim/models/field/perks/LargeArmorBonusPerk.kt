package me.nickp0is0n.escapefromthicgrim.models.field.perks

import me.nickp0is0n.escapefromthicgrim.models.PlayerProperty

class LargeArmorBonusPerk: CellPerk {
    override fun getPropertyChange(): Pair<PlayerProperty, Int> {
        return Pair(PlayerProperty.ARMOR, +30)
    }

    override fun getPerkUIName(): String = "Steel-skin(tm) one-of-a-kind(c) patented(R) extra strong potion"
}