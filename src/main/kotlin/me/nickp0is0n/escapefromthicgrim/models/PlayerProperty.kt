package me.nickp0is0n.escapefromthicgrim.models

enum class PlayerProperty {
    HEALTH, ARMOR, STAMINA;

    override fun toString(): String = when(this) {
        HEALTH -> "Health"
        ARMOR -> "Armor"
        STAMINA -> "Stamina"
    }
}