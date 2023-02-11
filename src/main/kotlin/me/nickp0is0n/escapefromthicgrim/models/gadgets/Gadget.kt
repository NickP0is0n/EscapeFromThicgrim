package me.nickp0is0n.escapefromthicgrim.models.gadgets

import me.nickp0is0n.escapefromthicgrim.models.PlayerProperty

interface Gadget {
    fun getPropertyChange(): Pair<PlayerProperty, Int>
    fun getStaminaChange(): Int
    fun getGadgetUIName(): String
}