package me.nickp0is0n.escapefromthicgrim.models.gadgets

import me.nickp0is0n.escapefromthicgrim.models.PlayerProperty

class CrimsonSpike: Gadget {
    override fun getPropertyChange(): Pair<PlayerProperty, Int> {
        return Pair(PlayerProperty.DAMAGE, +110)
    }

    override fun getStaminaChange(): Int {
        return 10
    }

    override fun getGadgetUIName(): String = "Crimson Spike"
}