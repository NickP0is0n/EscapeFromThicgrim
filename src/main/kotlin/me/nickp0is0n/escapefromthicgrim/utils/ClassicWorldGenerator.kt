package me.nickp0is0n.escapefromthicgrim.utils

import me.nickp0is0n.escapefromthicgrim.models.field.FieldCell
import me.nickp0is0n.escapefromthicgrim.models.field.PerkAndEntityPool
import me.nickp0is0n.escapefromthicgrim.models.field.PlayField
import me.nickp0is0n.escapefromthicgrim.models.field.entities.BasicSellerEntity
import me.nickp0is0n.escapefromthicgrim.models.field.entities.CellEntity
import me.nickp0is0n.escapefromthicgrim.models.field.perks.CellPerk
import kotlin.random.Random
import kotlin.reflect.KType
import kotlin.reflect.full.createType
import kotlin.reflect.full.primaryConstructor

const val PERK_SPAWN_PROBABILITY = 4
const val FRIENDLY_ENTITY_SPAWN_PROBABILITY = 3

class ClassicWorldGenerator(private val difficulty: Int): WorldGenerator {
    override fun generateFieldCell(): PlayField {
        val logger: Logger = BasicConsoleLogger()
        val field = mutableListOf<MutableList<FieldCell>>()
        for (i in 0 until 128) {
            field.add(mutableListOf())
            for (j in 0 until 128) {
                var perk: CellPerk? = null
                var entity: CellEntity? = null
                if(Random.nextInt(0, 10) in 0..PERK_SPAWN_PROBABILITY) {
                    perk = PerkAndEntityPool.perkPool[Random.nextInt(0, PerkAndEntityPool.perkPool.size)]
                }
                if(Random.nextInt(0, 10) in 0..FRIENDLY_ENTITY_SPAWN_PROBABILITY) {
                    entity = BasicSellerEntity()
                }
                if(entity == null && Random.nextInt(0, 30) in 0..difficulty) {
                    val entityID = Random.nextInt(PerkAndEntityPool.entityPool.size)
                    if (PerkAndEntityPool.entityPool[entityID].primaryConstructor!!.parameters[0].name != "difficultyLevel") { // should probably check for type. don't know how atm
                        logger.warning("${PerkAndEntityPool.entityPool[entityID].simpleName} is not compatible with current world generator and therefore will not be used for this session.")
                    }
                    entity = PerkAndEntityPool.entityPool[entityID].primaryConstructor!!.call(difficulty) // at least without mods guaranteed to be aggressive entity
                }
                field[i].add(FieldCell(entity, perk))
            }
        }
        return PlayField(128, 128, Pair(Random.nextInt(0, 128), Random.nextInt(0, 128)), field)
    }
}