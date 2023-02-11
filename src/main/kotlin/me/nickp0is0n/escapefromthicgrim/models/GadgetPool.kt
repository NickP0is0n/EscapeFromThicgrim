package me.nickp0is0n.escapefromthicgrim.models

import me.nickp0is0n.escapefromthicgrim.models.gadgets.*
import kotlin.reflect.KClass

object GadgetPool {
    val pool: List<KClass<out Gadget>> = listOf(
        BlackLotus::class,
        CrimsonSpike::class,
        DragonsTooth::class,
        IceHeart::class,
        IronClad::class,
        MoonBlade::class,
        ShadowFang::class,
        ThunderStrike::class
    )
}