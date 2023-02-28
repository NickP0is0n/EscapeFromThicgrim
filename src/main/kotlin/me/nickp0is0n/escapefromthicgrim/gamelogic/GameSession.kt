package me.nickp0is0n.escapefromthicgrim.gamelogic

import me.nickp0is0n.escapefromthicgrim.models.Player
import me.nickp0is0n.escapefromthicgrim.models.PlayerProperty
import me.nickp0is0n.escapefromthicgrim.models.field.PlayField
import me.nickp0is0n.escapefromthicgrim.models.field.entities.AggressiveCellEntity
import me.nickp0is0n.escapefromthicgrim.models.field.entities.SellerCellEntity
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
                uiHandler.displayPerkInfo(perk)
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
                uiHandler.displayMobEncounter(entity)
                state = GameState.COMBAT
            }

            field.cells[field.playerPosition.first][field.playerPosition.second].entity is SellerCellEntity -> {
                state = GameState.TRADE
                val entity: SellerCellEntity =
                    field.cells[field.playerPosition.first][field.playerPosition.second].entity as SellerCellEntity
                uiHandler.displaySellerEncounter(entity)
                uiHandler.displaySellerItems(entity)
            }

            else -> {
                uiHandler.displayEmptyCell()
            }
        }
    }

    fun entityAttack(entity: AggressiveCellEntity, player: Player) {
        uiHandler.displayDamage(entity)
        val damageAbsorbed: Int
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
        if (player.health <= 0) {
            state = GameState.GAMEOVER
            uiHandler.gameOverLost()
        }
    }

    fun incrementMove() {
        currentMove++
    }
}