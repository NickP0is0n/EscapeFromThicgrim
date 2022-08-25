package me.nickp0is0n.escapefromthicgrim.utils

import me.nickp0is0n.escapefromthicgrim.models.field.PlayField

interface WorldGenerator {
    fun generateFieldCell(): PlayField
}