package me.nickp0is0n.escapefromthicgrim.models.field

import me.nickp0is0n.escapefromthicgrim.models.field.entities.AggressiveCellEntity
import me.nickp0is0n.escapefromthicgrim.models.field.entities.BronzebreathEntity
import me.nickp0is0n.escapefromthicgrim.models.field.perks.HealthBonusPerk
import kotlin.reflect.KClass

object PerkAndEntityPool {
    val perkPool = listOf(HealthBonusPerk())
    val entityPool: List<KClass<out AggressiveCellEntity>> = listOf(BronzebreathEntity::class)
}