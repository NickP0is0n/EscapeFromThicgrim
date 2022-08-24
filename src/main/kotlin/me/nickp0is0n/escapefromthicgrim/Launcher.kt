package me.nickp0is0n.escapefromthicgrim

import me.nickp0is0n.escapefromthicgrim.models.Player
import me.nickp0is0n.escapefromthicgrim.models.field.FieldCell
import me.nickp0is0n.escapefromthicgrim.models.field.PlayField
import java.util.*
import kotlin.random.Random


//TODO: proper console UI
fun main() {
    println("Escape From Thicgrim")
    println("Console Edition")
    println("Version 0.0.1, Created by Mykola Chaikovskyi on 24.08.2022\n")
}

fun initializeGameSettings() {
    var input = Scanner(System.`in`)

    println("Please enter your character's nickname (ex. Jacob): ")
    val player = Player(input.nextLine())
    println("Please enter the difficulty level (1-20): ")
    val difficultyLevel = input.nextInt()
    player.health = 1000 - ((difficultyLevel - 1) * 20)
    player.armor = 0
    player.cash = Random.nextInt(0, (100 - (difficultyLevel - 1) * 5))
    player.stamina = 100

    val field = PlayField(playerPosition = Pair(Random.nextInt(0, 128), Random.nextInt(0, 128)), cells = STUB_generatePlayfield())

}

fun STUB_generatePlayfield(): MutableList<MutableList<FieldCell>> {
    val field = mutableListOf<MutableList<FieldCell>>()
    for (i in 0 until 128) {
        field.add(mutableListOf())
        for (j in 0 until 128) {
            field[i].add(FieldCell(null, null))
        }
    }

    return field
}