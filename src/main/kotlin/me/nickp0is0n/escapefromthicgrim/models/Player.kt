package me.nickp0is0n.escapefromthicgrim.models

import me.nickp0is0n.escapefromthicgrim.models.gadgets.Gadget
import java.io.Serializable

data class Player(
    var nickname: String = "Jacob",
    var health: Int = 0,
    var armor: Int = 0,
    var stamina: Int = 0,
    var cash: Int = 0,
    var damage: Int = 100,
    val gadgets: MutableList<Gadget> = mutableListOf()
): Serializable
