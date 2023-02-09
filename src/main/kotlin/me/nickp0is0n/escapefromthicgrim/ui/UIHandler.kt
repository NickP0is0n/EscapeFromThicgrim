package me.nickp0is0n.escapefromthicgrim.ui

import me.nickp0is0n.escapefromthicgrim.models.field.entities.AggressiveCellEntity
import me.nickp0is0n.escapefromthicgrim.models.field.perks.CellPerk

interface UIHandler {
    fun updateSessionInfo()
    fun displayAvailableCommands()
    fun displayCharacterMovement(direction: Direction)
    fun displayMobArmorHit(attackStrength: Int, remainArmor: Int)
    fun displayMobHit(attackStrength: Int)
    fun displayMobKill(entity: AggressiveCellEntity)
    fun displayAttackBack(entity: AggressiveCellEntity)
    fun displayEscapeResult(isEscaped: Boolean)
    fun displayMobEncounter(entity: AggressiveCellEntity)
    fun displayEmptyCell()
    fun displayPerkInfo(perk: CellPerk)
    fun displayDamage(entity: AggressiveCellEntity)
}