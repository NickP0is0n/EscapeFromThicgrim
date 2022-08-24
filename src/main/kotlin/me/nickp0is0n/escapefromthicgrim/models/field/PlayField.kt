package me.nickp0is0n.escapefromthicgrim.models.field

import java.io.Serializable

data class PlayField(
    val height: Int = 128,
    val width: Int = 128,
    val playerPosition: Pair<Int, Int> = Pair(0,0),
    val cells: MutableList<MutableList<FieldCell>>
): Serializable
