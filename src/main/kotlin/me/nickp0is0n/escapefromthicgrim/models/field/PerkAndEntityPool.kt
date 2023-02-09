package me.nickp0is0n.escapefromthicgrim.models.field

import me.nickp0is0n.escapefromthicgrim.models.field.entities.*
import me.nickp0is0n.escapefromthicgrim.models.field.perks.CellPerk
import me.nickp0is0n.escapefromthicgrim.models.field.perks.HealthBonusPerk
import me.nickp0is0n.escapefromthicgrim.models.field.perks.LargeArmorBonusPerk
import me.nickp0is0n.escapefromthicgrim.models.field.perks.SmallArmorBonusPerk
import kotlin.reflect.KClass

object PerkAndEntityPool {
    val perkPool: List<KClass<out CellPerk>> = listOf(
        HealthBonusPerk::class,
        SmallArmorBonusPerk::class,
        LargeArmorBonusPerk::class
    )
        //listOf(HealthBonusPerk(), SmallArmorBonusPerk(), LargeArmorBonusPerk())
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