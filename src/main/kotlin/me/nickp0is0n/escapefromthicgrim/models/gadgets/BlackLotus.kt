package me.nickp0is0n.escapefromthicgrim.models.gadgets

import me.nickp0is0n.escapefromthicgrim.models.PlayerProperty

class BlackLotus: Gadget {
    override fun getPropertyChange(): Pair<PlayerProperty, Int> {
        return Pair(PlayerProperty.DAMAGE, +180)
    }

    override fun getStaminaChange(): Int {
        return 10
    }

    override fun getGadgetUIName(): String = "Black Lotus"
}