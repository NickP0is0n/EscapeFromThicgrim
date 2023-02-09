package me.nickp0is0n.escapefromthicgrim.models.field

import me.nickp0is0n.escapefromthicgrim.models.field.entities.*
import me.nickp0is0n.escapefromthicgrim.models.field.perks.*
import kotlin.reflect.KClass

object PerkAndEntityPool {
    val perkPool: List<KClass<out CellPerk>> = listOf(
        HealthBonusPerk::class,
        SmallArmorBonusPerk::class,
        LargeArmorBonusPerk::class,
        SmallDamageBonusPerk::class,
        MediumDamageBonusPerk::class,
        LargeDamageBonusPerk::class
    )

    val entityPool: List<KClass<out AggressiveCellEntity>> = listOf(
        BronzebreathEntity::class,
        ForestNymphEntity::class,
        EvilKnightEntity::class,
        SentientRobotEntity::class,
        GuardEntity::class,
        PoisonFrogEntity::class,
        WizardEntity::class
    )
}