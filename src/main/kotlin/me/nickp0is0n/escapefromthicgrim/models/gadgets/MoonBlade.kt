package me.nickp0is0n.escapefromthicgrim.models.gadgets

import me.nickp0is0n.escapefromthicgrim.models.PlayerProperty

class MoonBlade: Gadget {
    override fun getPropertyChange(): Pair<PlayerProperty, Int> {
        return Pair(PlayerProperty.DAMAGE, +300)
    }

    override fun getStaminaChange(): Int {
        return 20
    }

    override fun getGadgetUIName(): String = "Moonblade"
}