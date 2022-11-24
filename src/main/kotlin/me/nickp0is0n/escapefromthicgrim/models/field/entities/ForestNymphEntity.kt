package me.nickp0is0n.escapefromthicgrim.models.field.entities

import kotlin.random.Random

class ForestNymphEntity(val difficultyLevel: Int): AggressiveCellEntity {
    private var health = 0
    private var damage = 0

    init {
        health = (40 * (Random.nextInt(1, difficultyLevel + 1)) / 2)
        damage = (80 * (Random.nextInt(1, difficultyLevel + 1)) / 2)
    }

    override fun getEntityName(): String {
        return "Forest nymph"
    }

    override fun getEntityDamage(): Int {
        return damage
    }

    override fun getEntityArmor(): Int {
        return 0
    }

    override fun reduceEntityHealth(amount: Int) {
        this.health -= amount
    }

    override fun getEntityHealth(): Int {
        return health
    }

    override fun reduceEntityArmor(amount: Int) {
        return
    }
}