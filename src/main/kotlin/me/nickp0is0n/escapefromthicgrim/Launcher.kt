package me.nickp0is0n.escapefromthicgrim

import me.nickp0is0n.escapefromthicgrim.gamelogic.CommandInterpreter
import me.nickp0is0n.escapefromthicgrim.gamelogic.GameSession
import me.nickp0is0n.escapefromthicgrim.gamelogic.GameState
import me.nickp0is0n.escapefromthicgrim.models.Player
import me.nickp0is0n.escapefromthicgrim.ui.ConsoleUIHandler
import me.nickp0is0n.escapefromthicgrim.utils.ClassicWorldGenerator
import me.nickp0is0n.escapefromthicgrim.utils.WorldGenerator
import java.util.*
import kotlin.random.Random


//TODO:proper console UI
fun main() {
    println("Escape From Thicgrim")
    println("Console Edition")
    println("Version 0.0.6, built on 09.02.2023")
    println("Created by Mykola Chaikovskyi\n")
    initializeGame()
}

fun initializeGame() {
    val gameSession = initializeGameSession()
    val interpreter = CommandInterpreter(gameSession)
    gameSession.state = GameState.FREEROAM
    while (true) {
        val input = Scanner(System.`in`)
        gameSession.uiHandler.updateSessionInfo()
        gameSession.uiHandler.displayAvailableCommands()
        interpreter.executeCommand(input.nextLine())
    }
}

private fun initializeGameSession(): GameSession {
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

    val session = GameSession(player, field, difficultyLevel)
    session.uiHandler = ConsoleUIHandler(session)

    return session
}


