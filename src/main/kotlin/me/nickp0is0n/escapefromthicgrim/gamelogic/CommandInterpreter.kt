package me.nickp0is0n.escapefromthicgrim.gamelogic

import me.nickp0is0n.escapefromthicgrim.models.PlayerProperty
import me.nickp0is0n.escapefromthicgrim.models.field.entities.AggressiveCellEntity
import me.nickp0is0n.escapefromthicgrim.models.field.entities.SellerCellEntity
import me.nickp0is0n.escapefromthicgrim.ui.Direction
import me.nickp0is0n.escapefromthicgrim.utils.BasicConsoleLogger
import kotlin.random.Random
import kotlin.random.nextInt

class CommandInterpreter (val session: GameSession) {
    fun executeCommand(command: String) {
        when (session.state) {
            GameState.FREEROAM -> {
                if (command.contains("move")) {
                    when (command.substring(5)) {
                        "n" -> {
                            if (session.field.playerPosition.second + 1 == session.field.height) {
                                session.uiHandler.displayWorldBound(Direction.NORTH)
                            }
                            else {
                                session.field.playerPosition = Pair(session.field.playerPosition.first, session.field.playerPosition.second + 1)
                                session.uiHandler.displayCharacterMovement(Direction.NORTH)
                            }
                        }

                        "s" -> {
                            if (session.field.playerPosition.second == 0) {
                                session.uiHandler.displayWorldBound(Direction.SOUTH)
                            }
                            else {
                                session.field.playerPosition = Pair(session.field.playerPosition.first, session.field.playerPosition.second - 1)
                                session.uiHandler.displayCharacterMovement(Direction.SOUTH)
                            }
                        }

                        "w" -> {
                            if (session.field.playerPosition.first == 0) {
                                session.uiHandler.displayWorldBound(Direction.WEST)
                            }
                            else {
                                session.field.playerPosition = Pair(session.field.playerPosition.first - 1, session.field.playerPosition.second)
                                session.uiHandler.displayCharacterMovement(Direction.WEST)
                            }
                        }

                        "e" -> {
                            if (session.field.playerPosition.first + 1 == session.field.width) {
                                session.uiHandler.displayWorldBound(Direction.EAST)
                            }
                            else {
                                session.field.playerPosition = Pair(session.field.playerPosition.first + 1, session.field.playerPosition.second)
                                session.uiHandler.displayCharacterMovement(Direction.EAST)
                            }
                        }
                    }
                }
                session.makeMoveAndFullyUpdateSessionInfo()
            }

            GameState.COMBAT -> {
                when (command) {
                    "attack" -> {
                        val entity = session.field.cells[session.field.playerPosition.first][session.field.playerPosition.second].entity as AggressiveCellEntity
                        val totalPlayerDamage = (session.player.damage + session.player.gadgets.sumOf {
                            if (it.getPropertyChange().first == PlayerProperty.DAMAGE) {
                                it.getPropertyChange().second
                            }
                            else 0
                        })
                        var damageAbsorbed = 0
                        if (entity.getEntityArmor() > 0) {
                            val possibleArmorDamage: Int = totalPlayerDamage / 2
                            damageAbsorbed = if (entity.getEntityArmor() >= possibleArmorDamage) {
                                possibleArmorDamage
                            } else {
                                entity.getEntityArmor()
                            }
                            entity.reduceEntityArmor(damageAbsorbed)
                            entity.reduceEntityHealth(totalPlayerDamage - damageAbsorbed)
                            session.uiHandler.displayMobArmorHit(damageAbsorbed, entity.getEntityArmor())
                        }
                        else {
                            entity.reduceEntityHealth(totalPlayerDamage)
                        }
                        session.uiHandler.displayMobHit(totalPlayerDamage - damageAbsorbed)
                        if (entity.getEntityHealth() <= 0) {
                            session.uiHandler.displayMobKill(entity)
                            session.field.cells[session.field.playerPosition.first][session.field.playerPosition.second].entity = null
                            session.state = GameState.FREEROAM
                        }
                        else {
                            session.uiHandler.displayAttackBack(entity)
                            session.entityAttack(entity, session.player)
                        }
                    }
                    "escape" -> {
                        if (Random.nextInt(0 until (20 + session.BASIC_ESCAPE_DIFFICULTY)) < session.difficulty + session.BASIC_ESCAPE_DIFFICULTY) {
                            session.uiHandler.displayEscapeResult(false)
                            session.entityAttack(session.field.cells[session.field.playerPosition.first][session.field.playerPosition.second].entity as AggressiveCellEntity, session.player)
                        }
                        else {
                            session.uiHandler.displayEscapeResult(true)
                            session.state = GameState.FREEROAM
                        }
                    }
                }
            }

            GameState.TRADE -> {
                when {
                    command.contains("buy") -> {
                        val entity: SellerCellEntity =
                            session.field.cells[session.field.playerPosition.first][session.field.playerPosition.second].entity as SellerCellEntity
                        val itemId = command.removePrefix("buy ").toInt()
                        if (itemId in 1..entity.getAvailableItems().size) {
                            //TODO:add currency management
                            val boughtItem = entity.getAvailableItems()[itemId - 1]
                            session.player.gadgets.add(boughtItem)
                            session.uiHandler.displaySoldItem(boughtItem)
                            if (boughtItem.getStaminaChange() > 0) {
                                session.uiHandler.displayStaminaDecrease(boughtItem)
                                session.player.stamina -= boughtItem.getStaminaChange()
                            }
                            entity.removeItemFromAvailable(boughtItem)
                        }
                    }

                    command == "leave" -> {
                        session.state = GameState.FREEROAM
                    }
                }
            }

            GameState.GAMEOVER -> {
                return
            }

            else -> {
                BasicConsoleLogger().error("While interpreting the command the game was in unknown state.")
            }
        }

        session.incrementMove()
    }
}