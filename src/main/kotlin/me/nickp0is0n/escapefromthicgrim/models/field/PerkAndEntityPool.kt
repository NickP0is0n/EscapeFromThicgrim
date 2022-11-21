package me.nickp0is0n.escapefromthicgrim.models.field

import me.nickp0is0n.escapefromthicgrim.models.field.entities.*
import me.nickp0is0n.escapefromthicgrim.models.field.perks.HealthBonusPerk
import kotlin.reflect.KClass

object PerkAndEntityPool {
    val perkPool = listOf(HealthBonusPerk())
    val entityPool: List<KClass<out AggressiveCellEntity>> = listOf(
        BronzebreathEntity::class, ForestNymphEntity::class, EvilKnightEntity::class, SentientRobotEntity::class
    )
}