package me.nickp0is0n.escapefromthicgrim.ui

import me.nickp0is0n.escapefromthicgrim.gamelogic.GameSession
import me.nickp0is0n.escapefromthicgrim.gamelogic.GameState
import me.nickp0is0n.escapefromthicgrim.models.field.entities.AggressiveCellEntity
import me.nickp0is0n.escapefromthicgrim.models.field.entities.SellerCellEntity
import me.nickp0is0n.escapefromthicgrim.models.field.perks.CellPerk
import me.nickp0is0n.escapefromthicgrim.models.gadgets.Gadget
import java.util.*

class ConsoleUIHandler(val session: GameSession): UIHandler {
    override fun updateSessionInfo() {
        println("Move ${session.currentMove}.")
        println("${session.player.nickname} is staying in position X: ${session.field.playerPosition.first}, Y: ${session.field.playerPosition.second}.")
        println("${session.player.nickname} is wearing: ${session.player.gadgets.joinToString(", ") { n -> n.getGadgetUIName() }}")
        println("${session.player.nickname}'s health: ${session.player.health}, your armor: ${session.player.armor}, your stamina: ${session.player.stamina}.")
    }

    override fun displayAvailableCommands() {
        print("Enter your command (available: ${
            when (session.state) {
                GameState.COMBAT -> "attack, escape"
                GameState.FREEROAM -> "move [n,w,s,e]"
                GameState.TRADE -> "buy [id of gadget] (wip), leave"
                else -> ""
            }
        }): ")
    }

    override fun displayCharacterMovement(direction: Direction) {
        when (direction) {
            Direction.NORTH -> println("Your character moved north.\n")
            Direction.WEST -> println("Your character moved west.\n")
            Direction.SOUTH -> println("Your character moved south.\n")
            Direction.EAST -> println("Your character moved east.\n")
        }
    }

    override fun displayMobArmorHit(attackStrength: Int, remainArmor: Int) {
        println("${session.player.nickname} hit their armor, reduced it to $remainArmor.")
    }

    override fun displayMobHit(attackStrength: Int) {
        println("${session.player.nickname} hit the mob, dealt $attackStrength of pure damage.")
    }

    override fun displayMobKill(entity: AggressiveCellEntity) {
        println("${session.player.nickname} killed ${entity.getEntityName()}.")
    }

    override fun displayAttackBack(entity: AggressiveCellEntity) {
        println("${entity.getEntityName()} still has ${entity.getEntityHealth()} health and ${entity.getEntityArmor()} armor points.")
        println("${entity.getEntityName()} attacked ${session.player.nickname} back.")
    }

    override fun displayEscapeResult(isEscaped: Boolean) {
        if (!isEscaped) {
            println("${session.player.nickname} tried to escape the fight, but got caught.")
        }
        else {
            println("${session.player.nickname} bravely retreated from a fight.") // p.s. what a loser
        }
    }

    override fun displayMobEncounter(entity: AggressiveCellEntity) {
        println("${session.player.nickname} encountered aggressive ${entity.getEntityName()}! Their health is at ${entity.getEntityHealth()} points and it can attack you with ${entity.getEntityDamage()}.")
        if (entity.getEntityArmor() > 0) {
            println("This mob also wears an armor, which improves their defence up ${entity.getEntityArmor()} points!")
        }
    }

    override fun displayEmptyCell() {
        println("Unfortunately, nothing was there.")
    }

    override fun displayPerkInfo(perk: CellPerk) {
        println("${session.player.nickname} can feel the ${perk.getPerkUIName()} in the zone. Your ${perk.getPropertyChange().first} is changed: ${perk.getPropertyChange().second}.")
    }

    override fun displayDamage(entity: AggressiveCellEntity) {
        println("${entity.getEntityDamage()} damage received.")
    }

    override fun displaySellerEncounter(entity: SellerCellEntity) {
        println("After this long path ${session.player.nickname} can have a bit of rest - they encountered friendly ${entity.getEntityName()}.")
        println("[note from developer] Sellers are currently work in progress.")
    }

    override fun displaySellerItems(entity: SellerCellEntity) {
        println("Available items to buy:")
        val itemList = entity.getAvailableItems()
        itemList.forEachIndexed { index, gadget ->
            println("${index + 1}. ${gadget.getGadgetUIName()}: It ${if(gadget.getPropertyChange().second < 0) "decreases" else "increases"} your ${gadget.getPropertyChange().first.toString().lowercase(Locale.getDefault())} by ${gadget.getPropertyChange().second} points. (Stamina decrease: ${gadget.getStaminaChange()})")
        }
        println()
    }

    override fun displaySoldItem(item: Gadget) {
        println("${session.player.nickname} just successfully bought ${item.getGadgetUIName()}.")
    }

    override fun gameOverLost() {
        println("""
            Despite all the persistence of ${session.player.nickname}, the last battle ended in complete failure. 
            ${session.player.nickname}'s last sighs were heard as his soul left his body. 
            Their blood was gradually absorbed by the surroundings of the place. 
            There is no telling how many more souls Thicgrim will take, 
            but for ${session.player.nickname} this was the last destination.
            
            GAME OVER.
        """.trimIndent())
    }


}