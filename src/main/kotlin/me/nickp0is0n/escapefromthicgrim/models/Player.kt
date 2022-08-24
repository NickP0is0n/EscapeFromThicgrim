package me.nickp0is0n.escapefromthicgrim.models

import java.io.Serializable

data class Player(
    var health: Int = 0,
    var armor: Int = 0,
    var stamina: Int = 0,
    val cash: Int = 0,
    val gadgets: MutableList<Gadget> = mutableListOf()
): Serializable
