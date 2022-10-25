package me.nickp0is0n.escapefromthicgrim.models.field.entities

import kotlin.random.Random

class BronzebreathEntity(val difficultyLevel: Int): AggressiveCellEntity { // basic aggressive mob, no armor
    private var health = 0

    init {
        health = 100 * (Random.nextInt(1, difficultyLevel) / 2)
    }

    override fun getEntityName(): String {
        return "Bronzebreath golem"
    }

    override fun getEntityDamage(): Int {
        return 50 * (Random.nextInt(1, difficultyLevel) / 2)
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
}