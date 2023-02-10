package me.nickp0is0n.escapefromthicgrim.gamelogic

import me.nickp0is0n.escapefromthicgrim.models.PlayerProperty
import me.nickp0is0n.escapefromthicgrim.models.field.entities.AggressiveCellEntity
import me.nickp0is0n.escapefromthicgrim.ui.Direction
import me.nickp0is0n.escapefromthicgrim.utils.BasicConsoleLogger
import kotlin.random.Random
import kotlin.random.nextInt

class CommandInterpreter (val session: GameSession) {
    fun executeCommand(command: String) {
        when (session.state) {
            GameState.FREEROAM -> {
                when (command.substring(5)) {
                    "n" -> {
                        session.field.playerPosition = Pair(session.field.playerPosition.first, session.field.playerPosition.second + 1)
                        session.uiHandler.displayCharacterMovement(Direction.NORTH)
                    }

                    "s" -> {
                        session.field.playerPosition = Pair(session.field.playerPosition.first, session.field.playerPosition.second - 1)
                        session.uiHandler.displayCharacterMovement(Direction.SOUTH)
                    }

                    "w" -> {
                        session.field.playerPosition = Pair(session.field.playerPosition.first - 1, session.field.playerPosition.second)
                        session.uiHandler.displayCharacterMovement(Direction.WEST)
                    }

                    "e" -> {
                        session.field.playerPosition = Pair(session.field.playerPosition.first + 1, session.field.playerPosition.second - 1)
                        session.uiHandler.displayCharacterMovement(Direction.EAST)
                    }
                }
                session.makeMoveAndFullyUpdateSessionInfo()
            }

            GameState.COMBAT -> {
                when (command) {
                    "attack" -> {
                        val entity = session.field.cells[session.field.playerPosition.first][session.field.playerPosition.second].entity as AggressiveCellEntity
                        val totalPlayerDamage = (session.player.damage + session.player.gadgets.sumOf { if (it.affectedProperties.count { it.first == PlayerProperty.DAMAGE } > 0) it.affectedProperties.find { it.first == PlayerProperty.DAMAGE }!!.second else 0})
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
                        println("Work in progress. Unavailable.")
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