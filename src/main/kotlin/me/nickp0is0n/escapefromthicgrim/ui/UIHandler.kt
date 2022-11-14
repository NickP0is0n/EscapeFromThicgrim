package me.nickp0is0n.escapefromthicgrim.ui

import me.nickp0is0n.escapefromthicgrim.models.field.entities.AggressiveCellEntity

interface UIHandler {
    fun updateSessionInfo()
    fun displayAvailableCommands()
    fun displayCharacterMovement(direction: Direction)
    fun displayMobArmorHit(attackStrength: Int, remainArmor: Int)
    fun displayMobHit(attackStrength: Int)
    fun displayMobKill(entity: AggressiveCellEntity)
    fun displayAttackBack(entity: AggressiveCellEntity)
    fun displayEscapeResult(isEscaped: Boolean)
}