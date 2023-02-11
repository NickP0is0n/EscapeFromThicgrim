package me.nickp0is0n.escapefromthicgrim.models.gadgets

import me.nickp0is0n.escapefromthicgrim.models.PlayerProperty

class ShadowFang: Gadget {
    override fun getPropertyChange(): Pair<PlayerProperty, Int> {
        return Pair(PlayerProperty.DAMAGE, +140)
    }

    override fun getStaminaChange(): Int {
        return 5
    }

    override fun getGadgetUIName(): String = "Shadowfang"
}