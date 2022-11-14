package me.nickp0is0n.escapefromthicgrim.gamelogic

import me.nickp0is0n.escapefromthicgrim.models.Player
import me.nickp0is0n.escapefromthicgrim.models.PlayerProperty
import me.nickp0is0n.escapefromthicgrim.models.field.PlayField
import me.nickp0is0n.escapefromthicgrim.models.field.entities.AggressiveCellEntity
import me.nickp0is0n.escapefromthicgrim.ui.UIHandler

class GameSession (val player: Player,
                   val field: PlayField,
                   val difficulty: Int
                   ) {
    lateinit var uiHandler: UIHandler
    var state: GameState = GameState.UNKNOWN
    val BASIC_ESCAPE_DIFFICULTY = 6
    var currentMove = 1

    fun makeMoveAndFullyUpdateSessionInfo() {
        when {
            field.cells[field.playerPosition.first][field.playerPosition.second].perk != null -> {
                val perk = field.cells[field.playerPosition.first][field.playerPosition.second].perk!!
                println("${player.nickname} can feel the ${perk.getPerkUIName()} in the zone. Your ${perk.getPropertyChange().first} is changed: ${perk.getPropertyChange().second}.")
                when (field.cells[field.playerPosition.first][field.playerPosition.second].perk!!.getPropertyChange().first) {
                    PlayerProperty.HEALTH -> player.health += perk.getPropertyChange().second
                    PlayerProperty.ARMOR -> player.armor += perk.getPropertyChange().second
                    PlayerProperty.STAMINA -> player.stamina += perk.getPropertyChange().second
                    PlayerProperty.DAMAGE -> player.damage += perk.getPropertyChange().second
                }
                field.cells[field.playerPosition.first][field.playerPosition.second].perk = null
            }

            field.cells[field.playerPosition.first][field.playerPosition.second].entity is AggressiveCellEntity -> {
                val entity: AggressiveCellEntity =
                    field.cells[field.playerPosition.first][field.playerPosition.second].entity as AggressiveCellEntity
                println("${player.nickname} encountered aggressive ${entity.getEntityName()}! Their health is at ${entity.getEntityHealth()} points and it can attack you with ${entity.getEntityDamage()}.")
                if (entity.getEntityArmor() > 0) {
                    println("This mob also wears an armor, which improves their defence up ${entity.getEntityArmor()} points!")
                }
                state = GameState.COMBAT
            }

            else -> {
                println("Unfortunately, nothing was there.")
            }
        }
    }

    fun entityAttack(entity: AggressiveCellEntity, player: Player) {
        println("${entity.getEntityDamage()} damage received.")
        var damageAbsorbed: Int
        val totalDamage =
            entity.getEntityDamage()
        if (player.armor > 0) {
            val possibleArmorDamage: Int = totalDamage / 2
            if (player.armor >= possibleArmorDamage) {
                damageAbsorbed = possibleArmorDamage
                player.armor -= possibleArmorDamage
            } else {
                damageAbsorbed = player.armor
                player.armor = 0
            }
            player.health -= totalDamage - damageAbsorbed
        } else {
            player.health -= totalDamage
        }
    }

    fun incrementMove() {
        currentMove++
    }
}