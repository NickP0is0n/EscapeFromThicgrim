package me.nickp0is0n.escapefromthicgrim.models.gadgets

import me.nickp0is0n.escapefromthicgrim.models.PlayerProperty

class IronClad: Gadget {
    override fun getPropertyChange(): Pair<PlayerProperty, Int> {
        return Pair(PlayerProperty.DAMAGE, +150)
    }

    override fun getStaminaChange(): Int {
        return 15
    }

    override fun getGadgetUIName(): String = "Ironclad"
}
