package me.nickp0is0n.escapefromthicgrim

import me.nickp0is0n.escapefromthicgrim.models.Player
import me.nickp0is0n.escapefromthicgrim.models.PlayerProperty
import me.nickp0is0n.escapefromthicgrim.models.field.PlayField
import me.nickp0is0n.escapefromthicgrim.models.field.entities.AggressiveCellEntity
import me.nickp0is0n.escapefromthicgrim.utils.ClassicWorldGenerator
import me.nickp0is0n.escapefromthicgrim.utils.WorldGenerator
import java.util.*
import kotlin.random.Random
import kotlin.random.nextInt


const val BASIC_ESCAPE_DIFFICULTY = 6
//TODO:proper console UI
fun main() {
    println("Escape From Thicgrim")
    println("Console Edition")
    println("Version 0.0.2, Created by Mykola Chaikovskyi on 24.08.2022\n")
    initializeGameSettings()
}

fun initializeGameSettings() {
    val input = Scanner(System.`in`)

    println("Please enter your character's nickname (ex. Jacob): ")
    val player = Player(input.nextLine())
    println("Please enter the difficulty level (1-20): ")
    val difficultyLevel = input.nextInt()
    player.health = 1000 - ((difficultyLevel - 1) * 20)
    player.armor = 0
    player.cash = Random.nextInt(0, (100 - (difficultyLevel - 1) * 5))
    player.stamina = 100

    val worldGenerator: WorldGenerator = ClassicWorldGenerator(difficultyLevel)
    val field = worldGenerator.generateFieldCell()
    STUB_gameplay(player, field, difficultyLevel)
}

fun STUB_gameplay(player: Player, field: PlayField, difficulty: Int)
{
    var currentMove = 0
    var attackAvailable = false
    var characterMoved = true
    while (true) {
        currentMove++
        println("Move $currentMove.")
        println("${player.nickname} is staying in position X: ${field.playerPosition.first}, Y: ${field.playerPosition.second}.")
        println("${player.nickname} is wearing: ${player.gadgets.joinToString(", ")}")
        println("${player.nickname}'s health: ${player.health}, your armor: ${player.armor}, your stamina: ${player.stamina}.")
        println("Enter your command (available: ${if (attackAvailable) "attack, escape" else "move [n,w,s,e]"}): ")
        val input = Scanner(System.`in`)
        while (true) {
            val command = input.nextLine()
            if (command.contains("move") && !attackAvailable) {
                characterMoved = true
                when (command.substring(5)) {
                    "n" -> {
                        field.playerPosition = Pair(field.playerPosition.first, field.playerPosition.second + 1)
                        println("Your character moved north.\n")
                    }

                    "s" -> {
                        field.playerPosition = Pair(field.playerPosition.first, field.playerPosition.second - 1)
                        println("Your character moved south.\n")
                    }

                    "w" -> {
                        field.playerPosition = Pair(field.playerPosition.first - 1, field.playerPosition.second)
                        println("Your character moved west.\n")
                    }

                    "e" -> {
                        field.playerPosition = Pair(field.playerPosition.first + 1, field.playerPosition.second - 1)
                        println("Your character moved east.\n")
                    }
                }
                break
            }
            else {
                when (command) {
                    "attack" -> {
                        TODO("Make a proper combat system")
                    }
                    "escape" -> {
                        if (Random.nextInt(0 until (20 + BASIC_ESCAPE_DIFFICULTY)) < difficulty + BASIC_ESCAPE_DIFFICULTY) {
                            println("${player.nickname} tried to escape the fight, but got caught.")
                            println("${(field.cells[field.playerPosition.first][field.playerPosition.second].entity as AggressiveCellEntity).getEntityDamage()} damage received.")
                            var damageAbsorbed: Int
                            val totalDamage = (field.cells[field.playerPosition.first][field.playerPosition.second].entity as AggressiveCellEntity).getEntityDamage()
                            if (player.armor > 0) {
                                val possibleArmorDamage: Int = totalDamage / 2
                                if (player.armor >= possibleArmorDamage) {
                                    damageAbsorbed = possibleArmorDamage
                                    player.armor -= possibleArmorDamage
                                }
                                else {
                                    damageAbsorbed = player.armor
                                    player.armor = 0
                                }
                                player.health -= totalDamage - damageAbsorbed
                            }
                            else {
                                player.health -= totalDamage
                            }
                        }
                        else {
                            println("${player.nickname} bravely retreated from a fight.") // p.s. what a loser
                            attackAvailable = false
                        }
                    }
                }
                break
            }
        }

        if (characterMoved) {
            when {
                field.cells[field.playerPosition.first][field.playerPosition.second].perk != null -> {
                    val perk = field.cells[field.playerPosition.first][field.playerPosition.second].perk!!
                    println("${player.nickname} can feel the ${perk.getPerkUIName()} in the zone. Your ${perk.getPropertyChange().first} is changed: ${perk.getPropertyChange().second}.")
                    //TODO: make another class for game logic
                    when(field.cells[field.playerPosition.first][field.playerPosition.second].perk!!.getPropertyChange().first) {
                        PlayerProperty.HEALTH -> player.health += perk.getPropertyChange().second
                        PlayerProperty.ARMOR -> player.armor += perk.getPropertyChange().second
                        PlayerProperty.STAMINA -> player.stamina += perk.getPropertyChange().second
                    }
                    field.cells[field.playerPosition.first][field.playerPosition.second].perk = null
                }

                field.cells[field.playerPosition.first][field.playerPosition.second].entity is AggressiveCellEntity -> {
                    val entity: AggressiveCellEntity = field.cells[field.playerPosition.first][field.playerPosition.second].entity as AggressiveCellEntity
                    println("${player.nickname} encountered aggressive ${entity.getEntityName()}! Their health is at ${entity.getEntityHealth()} points and it can attack you with ${entity.getEntityDamage()}.")
                    if (entity.getEntityArmor() > 0) {
                        println("This mob also wears an armor, which improves their defence up ${entity.getEntityArmor()} points!")
                    }
                    attackAvailable = true
                }

                else -> {
                    println("Unfortunately, nothing was there.")
                }
            }

            characterMoved = false
        }
    }
}