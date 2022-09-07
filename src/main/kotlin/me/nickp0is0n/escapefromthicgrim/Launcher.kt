package me.nickp0is0n.escapefromthicgrim

import me.nickp0is0n.escapefromthicgrim.models.Player
import me.nickp0is0n.escapefromthicgrim.models.PlayerProperty
import me.nickp0is0n.escapefromthicgrim.models.field.PerkAndEntityPool
import me.nickp0is0n.escapefromthicgrim.models.field.PlayField
import me.nickp0is0n.escapefromthicgrim.utils.ClassicWorldGenerator
import me.nickp0is0n.escapefromthicgrim.utils.WorldGenerator
import java.util.*
import kotlin.random.Random
import kotlin.reflect.full.primaryConstructor


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
    STUB_gameplay(player, field)
}

fun STUB_gameplay(player: Player, field: PlayField)
{
    var currentMove = 0
    while (true) {
        currentMove++
        println("Move $currentMove.")
        println("${player.nickname} is staying in position X: ${field.playerPosition.first}, Y: ${field.playerPosition.second}.")
        println("${player.nickname} is wearing: ${player.gadgets.joinToString(", ")}")
        println("${player.nickname}'s health: ${player.health}, your armor: ${player.armor}, your stamina: ${player.stamina}.")
        println("Enter your command (available: move [n,w,s,e]: ")
        val input = Scanner(System.`in`)
        val command = input.nextLine()
        when(command.substring(5)) {
            "n" -> {
                field.playerPosition = Pair(field.playerPosition.first, field.playerPosition.second + 1)
                println("Your character moved north and encountered nothing.\n")
            }
            "s" -> {
                field.playerPosition = Pair(field.playerPosition.first, field.playerPosition.second - 1)
                println("Your character moved south and encountered nothing.\n")
            }
            "w" -> {
                field.playerPosition = Pair(field.playerPosition.first - 1, field.playerPosition.second)
                println("Your character moved west and encountered nothing.\n")
            }
            "e" -> {
                field.playerPosition = Pair(field.playerPosition.first + 1, field.playerPosition.second - 1)
                println("Your character moved east and encountered nothing.\n")
            }
        }
        if (field.cells[field.playerPosition.first][field.playerPosition.second].perk != null) {
            val perk = field.cells[field.playerPosition.first][field.playerPosition.second].perk!!
            println("${player.nickname} can feel the ${perk.getPerkUIName()} in the zone. Your ${perk.getPropertyChange().first} is changed: ${perk.getPropertyChange().second}.")
            //TODO: make another class for game logic
            when(field.cells[field.playerPosition.first][field.playerPosition.second].perk!!.getPropertyChange().first) {
                PlayerProperty.HEALTH -> player.health += perk.getPropertyChange().second
                PlayerProperty.ARMOR -> player.armor += perk.getPropertyChange().second
                PlayerProperty.STAMINA -> player.stamina += perk.getPropertyChange().second
            }
        }
    }
}