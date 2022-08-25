package me.nickp0is0n.escapefromthicgrim.utils

import me.nickp0is0n.escapefromthicgrim.models.field.FieldCell
import me.nickp0is0n.escapefromthicgrim.models.field.PerkAndEntityPool
import me.nickp0is0n.escapefromthicgrim.models.field.PlayField
import me.nickp0is0n.escapefromthicgrim.models.field.perks.CellPerk
import kotlin.random.Random

const val PERK_SPAWN_PROBABILITY = 4

class ClassicWorldGenerator: WorldGenerator {
    override fun generateFieldCell(): PlayField {
        val field = mutableListOf<MutableList<FieldCell>>()
        for (i in 0 until 128) {
            field.add(mutableListOf())
            for (j in 0 until 128) {
                var perk: CellPerk? = null
                if(Random.nextInt(0, 10) in 0..PERK_SPAWN_PROBABILITY) {
                    perk = PerkAndEntityPool.perkPool[Random.nextInt(0, PerkAndEntityPool.perkPool.size)]
                }
                field[i].add(FieldCell(null, perk))
            }
        }
        return PlayField(128, 128, Pair(Random.nextInt(0, 128), Random.nextInt(0, 128)), field)
    }
}