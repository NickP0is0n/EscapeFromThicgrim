package me.nickp0is0n.escapefromthicgrim.models

import java.io.Serializable

data class Gadget(
    val uiName: String,
    val affectedProperties: List<Pair<PlayerProperty, Int>>
): Serializable
